package com.ppepper.order.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.dto.OrderSkuDTO;
import com.ppepper.common.feign.GoodsFeignService;
import com.ppepper.common.model.Page;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.order.domain.OrderDO;
import com.ppepper.order.domain.OrderSkuDO;
import com.ppepper.order.mapper.OrderMapper;
import com.ppepper.order.mapper.OrderSkuMapper;
import org.springframework.beans.BeanUtils;
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
    public OrderDTO get(Long id) {
        OrderDO orderDO = orderMapper.selectById(id);
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderDO, orderDTO);

        List<OrderSkuDO> orderSkuDOList = orderSkuMapper.selectList(new EntityWrapper<OrderSkuDO>().eq("order_id", orderDTO.getId()));
        List<OrderSkuDTO> orderSkuDTOList = new ArrayList<>();
        for (OrderSkuDO orderSkuDO : orderSkuDOList) {
            OrderSkuDTO orderSkuDTO = new OrderSkuDTO();
            BeanUtils.copyProperties(orderSkuDO, orderSkuDTO);

            orderSkuDTO.setSpuDTO(goodsFeignService.get(orderSkuDTO.getSpuId()));
            orderSkuDTOList.add(orderSkuDTO);
        }
        orderDTO.setSkuList(orderSkuDTOList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> list(Integer pageNo, Integer pageSize, Integer status, Long userId) {
        List<OrderDTO> orderDTOList = orderMapper.selectOrderPage(status, (pageNo - 1) * pageSize, pageSize, userId);
        Long count = orderMapper.countOrder(status, (pageNo - 1) * pageSize, pageSize, userId);
        //封装SKU
        orderDTOList.forEach(item -> {
            List<OrderSkuDO> orderSkuDOList = orderSkuMapper.selectList(new EntityWrapper<OrderSkuDO>().eq("order_id", item.getId()));
            List<OrderSkuDTO> orderSkuDTOList = new ArrayList<>();
            for (OrderSkuDO orderSkuDO : orderSkuDOList) {
                OrderSkuDTO orderSkuDTO = new OrderSkuDTO();
                BeanUtils.copyProperties(orderSkuDO, orderSkuDTO);
                orderSkuDTOList.add(orderSkuDTO);
            }
            item.setSkuList(orderSkuDTOList);
        });
        return new Page<>(orderDTOList, pageNo, pageSize, count);
    }
}
