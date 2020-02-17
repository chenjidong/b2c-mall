package com.ppepper.order.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.dto.*;
import com.ppepper.common.enums.CouponStatusType;
import com.ppepper.common.enums.CouponType;
import com.ppepper.common.enums.OrderStatusType;
import com.ppepper.common.feign.*;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.order.domain.OrderDO;
import com.ppepper.order.domain.OrderSkuDO;
import com.ppepper.order.mapper.OrderMapper;
import com.ppepper.order.mapper.OrderSkuMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-06
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderSkuMapper orderSkuMapper;

    @Autowired
    private GoodsSysFeignService goodsSysFeignService;

    @Autowired
    private AccountSysFeignService accountSysFeignService;

    @Autowired
    private CartApiFeignService cartApiFeignService;


    @Autowired
    private AddressApiFeignService addressApiFeignService;

    @Autowired
    private CouponApiFeignService couponApiFeignService;

    @Autowired
    private CouponSysFeignService couponSysFeignService;


    @Override
    public AjaxResult get(Long id, Long accountId) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(id);
        orderDO.setAccountId(accountId);
        orderDO = orderMapper.selectOne(orderDO);
        OrderDTO orderDTO = copyProperties(orderDO, OrderDTO.class);
        if (orderDTO == null)
            return error("订单不存在");
        List<OrderSkuDO> orderSkuDOList = orderSkuMapper.selectList(new EntityWrapper<OrderSkuDO>().eq("order_id", orderDTO.getId()));
        if (orderSkuDOList != null && !orderSkuDOList.isEmpty()) {
            List<OrderSkuDTO> orderSkuDTOList = new ArrayList<>();
            for (OrderSkuDO orderSkuDO : orderSkuDOList) {
                OrderSkuDTO orderSkuDTO = copyProperties(orderSkuDO, OrderSkuDTO.class);

                orderSkuDTO.setSpuDTO(goodsSysFeignService.get(orderSkuDTO.getSpuId()));
                orderSkuDTOList.add(orderSkuDTO);
            }
            orderDTO.setSkuList(orderSkuDTOList);
        }

        return toAjax(orderDTO);
    }

    @Override
    public AjaxResult list(Integer pageNo, Integer pageSize, Integer status, Long accountId, String orderBy, Boolean isAsc) {
        Wrapper<OrderDO> wrapper = new EntityWrapper<>();

        if (status != null)
            wrapper.eq("status", status);
        if (orderBy != null && isAsc != null)
            wrapper.orderBy(orderBy, isAsc);
        if (accountId != 0)
            wrapper.eq("account_id", accountId);
        List<OrderDO> orderDOList = orderMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        if (orderDOList != null && !orderDOList.isEmpty()) {
            List<OrderDTO> orderDTOList = copyListProperties(orderDOList, OrderDTO.class);
            orderDTOList.forEach(item -> {
                List<OrderSkuDO> orderSkuDOList = orderSkuMapper.selectList(new EntityWrapper<OrderSkuDO>().eq("order_id", item.getId()));

                if (orderSkuDOList != null && !orderSkuDOList.isEmpty())
                    item.setSkuList(copyListProperties(orderSkuDOList, OrderSkuDTO.class));
            });

            return success(orderDTOList);
        }

        return error("查询错误");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult createByCart(Long accountId, Long addressId, Long couponId, String channel) {
        AccountDTO accountDTO = accountSysFeignService.get(accountId);
        if (accountDTO == null)
            return error("无效的用户");

        List<CartDTO> cartDTOList = cartApiFeignService.getCartList(1, 999);
        if (cartDTOList == null || cartDTOList.isEmpty())
            return error("购物车是空的");
        AddressDTO addressDTO = addressApiFeignService.getAddress(addressId);
        if (addressDTO == null)
            return error("无效的地址");
//            addressDTO = new AddressDTO();
//        addressDTO.setProvince("省会");
//        addressDTO.setCity("城市");
//        addressDTO.setCounty("区域");
//        addressDTO.setAddress("地址");
//        addressDTO.setPhone("电话");
//        addressDTO.setConsignee("收货人");
        CouponUserDTO couponUserDTO = null;
        if (couponId != null) {
            couponUserDTO = couponApiFeignService.getUserCoupon(couponId);
            if (couponUserDTO == null || couponUserDTO.getGmtUsed() != null || new Date().before(couponUserDTO.getGmtEnd()) || couponUserDTO.getCouponDTO().getStatus() != CouponStatusType.LOCK.getCode())
                return error("优惠券不存在/已使用");

            for (CartDTO dto : cartDTOList) {
                if (dto.getSpuDTO().getCategoryId() != couponUserDTO.getCouponDTO().getCategoryId()) {
                    return error(dto.getSpuDTO().getTitle() + " 不支持该优惠券");
                }
            }
        }

        Object point = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        Integer totalSkuPrice = 0;//不包含运费
        Integer actualSkuPrice = 0;//实际需要支付的价格 及扣掉所有优惠券及运费
        Integer totalSkuOriginPrice = 0;
        Integer totalFreight = 0;
        Integer totalCoupon = 0;

        try {

            for (CartDTO cartDTO : cartDTOList) {
                SpuDTO spuDTO = cartDTO.getSpuDTO();
                SpuSkuDTO spuSkuDTO = spuDTO.getSkuList().get(0);

                totalSkuPrice += spuSkuDTO.getPrice() * cartDTO.getNum();
                totalSkuOriginPrice = spuSkuDTO.getOriginalPrice() * cartDTO.getNum();
            }

            actualSkuPrice = totalSkuPrice;

            // TODO: 2020-02-12 计算优惠券
            if (couponUserDTO != null) {
                if (couponUserDTO.getCouponDTO().getType() == CouponType.COMMON.getCode()) {//普通优惠券
                    if (actualSkuPrice >= couponUserDTO.getCouponDTO().getMin()) {
                        totalCoupon = couponUserDTO.getCouponDTO().getDiscount();
                        actualSkuPrice = actualSkuPrice - totalCoupon;
                    }
                }
            }

            // TODO: 2020-02-12 计算运费

            OrderDO orderDO = new OrderDO();

            orderDO.setAccountId(accountId);
            orderDO.setAddress(addressDTO.getAddress());
            orderDO.setChannel(channel);
            orderDO.setCity(addressDTO.getCity());
            orderDO.setCounty(addressDTO.getCounty());
            orderDO.setPhone(addressDTO.getPhone());
            orderDO.setConsignee(addressDTO.getConsignee());
            orderDO.setSkuTotalPrice(totalSkuPrice);
            orderDO.setSkuOriginalTotalPrice(totalSkuOriginPrice);
            orderDO.setActualPrice(actualSkuPrice);
            orderDO.setFreightPrice(totalFreight);

            orderDO.setOrderNo("b2c_" + System.currentTimeMillis());
            orderDO.setStatus(OrderStatusType.UNPAY.getCode());
            orderDO.setCouponId(couponId);
            orderDO.setCouponPrice(totalCoupon);

            orderDO.setGmtCreate(new Date());
            orderDO.setGmtUpdate(new Date());

            orderMapper.insert(orderDO);


            for (CartDTO cartDTO : cartDTOList) {
                SpuDTO spuDTO = cartDTO.getSpuDTO();
                SpuSkuDTO spuSkuDTO = spuDTO.getSkuList().get(0);


                OrderSkuDO orderSkuDO = new OrderSkuDO();
                orderSkuDO.setNum(cartDTO.getNum());
                orderSkuDO.setSkuId(spuSkuDTO.getId());
                orderSkuDO.setSpuId(spuDTO.getId());

                orderSkuDO.setOrderId(orderDO.getId());
                orderSkuDO.setOrderNo(orderDO.getOrderNo());

                orderSkuDO.setSpuTitle(spuDTO.getTitle());

                orderSkuDO.setTitle(spuSkuDTO.getTitle());
                orderSkuDO.setBarCode(spuSkuDTO.getBarCode());

                orderSkuDO.setUnit(spuSkuDTO.getUnit());

                orderSkuDO.setPrice(spuSkuDTO.getPrice());
                orderSkuDO.setOriginalPrice(spuSkuDTO.getOriginalPrice());
                orderSkuDO.setImg(spuSkuDTO.getImg());
                orderSkuDO.setGmtCreate(new Date());
                orderSkuDO.setGmtUpdate(new Date());

                orderSkuMapper.insert(orderSkuDO);

                Boolean isStock = goodsSysFeignService.freezeStock(spuSkuDTO.getId(), cartDTO.getNum()); //冻结库存不成功直接抛异常回滚

                Boolean finished = cartApiFeignService.cleanCart();
                if (!isStock || !finished) {
                    throw new RuntimeException();
                }
            }

            if (couponUserDTO != null) {
                Boolean setUsedCoupon = couponSysFeignService.used(orderDO.getAccountId(), couponId);
                if (!setUsedCoupon)
                    throw new RuntimeException();
            }

            return success("构建订单成功", orderDO);

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(point);
        }


        return error("构建订单失败！");
    }

    @Override
    public AjaxResult getByOrderNo(Long accountId, String orderNo) {
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderNo(orderNo);
        orderDO.setAccountId(accountId);
        orderDO = orderMapper.selectOne(orderDO);
        OrderDTO orderDTO = copyProperties(orderDO, OrderDTO.class);
        if (orderDTO == null)
            return error("订单不存在");

        List<OrderSkuDO> orderSkuDOList = orderSkuMapper.selectList(new EntityWrapper<OrderSkuDO>().eq("order_id", orderDTO.getId()));
        if (orderSkuDOList != null && !orderSkuDOList.isEmpty()) {
            List<OrderSkuDTO> orderSkuDTOList = new ArrayList<>();
            for (OrderSkuDO orderSkuDO : orderSkuDOList) {
                OrderSkuDTO orderSkuDTO = copyProperties(orderSkuDO, OrderSkuDTO.class);

                orderSkuDTO.setSpuDTO(goodsSysFeignService.get(orderSkuDTO.getSpuId()));
                orderSkuDTOList.add(orderSkuDTO);
            }
            orderDTO.setSkuList(orderSkuDTOList);
        }
        return toAjax(orderDTO);
    }

    @Override
    public AjaxResult setStatus(Long accountId, String orderNo, Integer status) {
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderNo(orderNo);
        orderDO.setAccountId(accountId);
        orderDO = orderMapper.selectOne(orderDO);
        OrderDTO orderDTO = copyProperties(orderDO, OrderDTO.class);
        if (orderDTO == null)
            return error("订单不存在");
        if (orderDTO.getStatus() == OrderStatusType.COMPLETE.getCode())
            return error("订单已完成");

        orderDO.setStatus(status);
        orderDO.setGmtUpdate(new Date());
        return toAjax(orderMapper.updateById(orderDO));
    }

    @Override
    public List<OrderDTO> selectExpireOrderNos(Integer status, Date time) {
        List<OrderDTO> list = orderMapper.selectExpireOrderNos(status, time);
        if (list != null) {
            list.forEach(item -> {
                List<OrderSkuDO> orderSkuDOList = orderSkuMapper.selectList(new EntityWrapper<OrderSkuDO>().eq("order_id", item.getId()));

                List<OrderSkuDTO> orderSkuDTOList = new ArrayList<>();
                for (OrderSkuDO orderSkuDO : orderSkuDOList) {
                    OrderSkuDTO orderSkuDTO = copyProperties(orderSkuDO, OrderSkuDTO.class);

                    orderSkuDTO.setSpuDTO(goodsSysFeignService.get(orderSkuDTO.getSpuId()));
                    orderSkuDTOList.add(orderSkuDTO);
                }
                item.setSkuList(orderSkuDTOList);
            });
        }
        return list;
    }

    @Override
    public int update(OrderDO orderDO) {
        Wrapper<OrderDO> wrapper = new EntityWrapper<>();
        wrapper.eq("order_no", orderDO.getOrderNo());
        return orderMapper.update(orderDO, wrapper);
    }

}
