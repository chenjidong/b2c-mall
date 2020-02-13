package com.ppepper.payment.controller;

import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
@RestController
@RequestMapping("/api/payment/user")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/getByPayNo")
    public AjaxResult getByPayNo(String payNo) {
       return paymentService.getByPayNo(JwtTokenUtils.getCurrentAccountIdByToken(),payNo);
    }

    @RequestMapping("/getByOrderNo")
    public AjaxResult getByOrderNo(String orderNo) {
       return paymentService.getByOrderNo(JwtTokenUtils.getCurrentAccountIdByToken(),orderNo);
    }

    @RequestMapping("/create")
    public AjaxResult create(String orderNo, String channel, Integer payType) {
        return paymentService.create(JwtTokenUtils.getCurrentAccountIdByToken(),orderNo,channel,payType);
    }
}
