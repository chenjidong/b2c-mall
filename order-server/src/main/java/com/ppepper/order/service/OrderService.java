package com.ppepper.order.service;


import com.ppepper.order.model.GoodsDTO;
import com.ppepper.order.model.Order;
import com.ppepper.order.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Service
public class OrderService {

    @Autowired
    private GoodsService goodsService;

    private static final Map<Long, Order> order_map = new HashMap<Long, Order>();

    static {
        List<OrderItem> orderDetailsList = new ArrayList<OrderItem>();
        for (Long i = 1L; i <= 10L; i++) {
            GoodsDTO goodsDTO = new GoodsDTO();
            goodsDTO.setGoodsId(i);
            orderDetailsList.add(new OrderItem(i, i, 5, goodsDTO));
            order_map.put(i, new Order(i, i, new Date(), new Date(), new ArrayList<OrderItem>(orderDetailsList)));
            System.out.println(order_map);
        }
    }

    public Order findOrderById(Long id) {
        Order order = order_map.get(id);
        for (OrderItem item : order.getItems()) {
            try {
                GoodsDTO goodsDTO = goodsService.findGoodsById(item.getGoodsDTO().getGoodsId());
                item.setGoodsDTO(goodsDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return order_map.get(id);
    }
}
