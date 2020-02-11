package com.ppepper.order.controller;


import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/get")
    public AjaxResult get(@RequestParam("id") Long id) {
        return orderService.get(id, JwtTokenUtils.getCurrentAccountIdByToken());
    }

    @RequestMapping(value = "/list")
    public AjaxResult list(@RequestParam("pageNo") Integer pageNo,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam("status") Integer status,
                           @RequestParam("orderBy") String orderBy,
                           @RequestParam("isAsc") Boolean isAsc) {

        return orderService.list(pageNo, pageSize, status, JwtTokenUtils.getCurrentAccountIdByToken(), orderBy, isAsc);
    }
}
