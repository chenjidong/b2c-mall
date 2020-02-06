package com.ppepper.order.controller;


import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.model.Page;
import com.ppepper.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/get")
    public OrderDTO get(@RequestParam("id") Long id) {
        return orderService.get(id);
    }

    @RequestMapping(value = "/list")
    public Page<OrderDTO> list(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam("status") Integer status, @RequestParam("userId") Long userId) {
        return orderService.list(pageNo, pageSize, status, userId);
    }
}
