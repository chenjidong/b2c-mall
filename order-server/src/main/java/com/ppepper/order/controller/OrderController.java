package com.ppepper.order.controller;


import com.ppepper.order.model.Order;
import com.ppepper.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{id}")
    public Order findOrderById(@PathVariable("id") Long id) {
        return orderService.findOrderById(id);
    }
}
