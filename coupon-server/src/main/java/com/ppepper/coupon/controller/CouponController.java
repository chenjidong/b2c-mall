package com.ppepper.coupon.controller;

import com.ppepper.common.dto.CouponDTO;
import com.ppepper.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @RequestMapping("/get")
    public CouponDTO get(@RequestParam("id") Long id) {
        return couponService.get(id);
    }
}
