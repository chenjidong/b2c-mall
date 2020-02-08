package com.ppepper.coupon.controller;

import com.ppepper.common.dto.CouponUserDTO;
import com.ppepper.common.model.Page;
import com.ppepper.coupon.service.CouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@RestController
@RequestMapping("/coupon/user")
public class CouponUserController {

    @Autowired
    private CouponUserService couponUserService;

    @RequestMapping("/getByUserId")
    public Page<CouponUserDTO> getByUserId(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return couponUserService.getByUserId(userId,pageNo,pageSize);
    }
}
