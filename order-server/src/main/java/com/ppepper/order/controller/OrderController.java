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

    @RequestMapping(value = "/getByOrderNo")
    public AjaxResult getByOrderNo(@RequestParam("orderNo") String orderNo) {
        return orderService.getByOrderNo(JwtTokenUtils.getCurrentAccountIdByToken(), orderNo);
    }

    @RequestMapping(value = "/setStatus")
    public AjaxResult setStatus(@RequestParam("orderNo") String orderNo, Integer status) {
        return orderService.setStatus(JwtTokenUtils.getCurrentAccountIdByToken(), orderNo, status);
    }
}
