package com.ppepper.order.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.dto.OrderSkuDTO;
import com.ppepper.common.feign.GoodsFeignService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.order.domain.OrderDO;
import com.ppepper.order.domain.OrderSkuDO;
import com.ppepper.order.mapper.OrderMapper;
import com.ppepper.order.mapper.OrderSkuMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private GoodsFeignService goodsFeignService;


    @Override
    public AjaxResult get(Long id, Long accountId) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(id);
        orderDO.setAccountId(accountId);
        orderDO = orderMapper.selectOne(orderDO);
        OrderDTO orderDTO = copyProperties(orderDO, OrderDTO.class);

        List<OrderSkuDO> orderSkuDOList = orderSkuMapper.selectList(new EntityWrapper<OrderSkuDO>().eq("order_id", orderDTO.getId()));
        if (orderSkuDOList != null && !orderSkuDOList.isEmpty()) {
            List<OrderSkuDTO> orderSkuDTOList = new ArrayList<>();
            for (OrderSkuDO orderSkuDO : orderSkuDOList) {
                OrderSkuDTO orderSkuDTO = copyProperties(orderSkuDO, OrderSkuDTO.class);

                orderSkuDTO.setSpuDTO(goodsFeignService.get(orderSkuDTO.getSpuId()));
                orderSkuDTOList.add(orderSkuDTO);
            }
            orderDTO.setSkuList(orderSkuDTOList);
        }

        return success(orderDTO);
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
}
