package com.ppepper.coupon.controller;

import com.ppepper.common.controller.BaseController;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.coupon.service.CouponService;
import com.ppepper.coupon.service.CouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@RestController
@RequestMapping("/api/coupon")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponUserService couponUserService;

    @RequestMapping("/get")
    public AjaxResult get(@RequestParam("id") Long id) {
        return couponService.get(id);
    }

    @RequestMapping("/list")
    public AjaxResult list(@RequestParam("pageNo") Integer pageNo,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam("categoryId") Long categoryId,
                           @RequestParam("status") Integer status,
                           @RequestParam("type") Integer type,
                           @RequestParam("orderBy") String orderBy,
                           @RequestParam("isAsc") Boolean isAsc,
                           @RequestParam("title") String title) {
        return couponService.list(pageNo, pageSize, categoryId, status, type, orderBy, isAsc, title);
    }


    @RequestMapping(value = "/rollbackUnused", method = RequestMethod.GET)
    public AjaxResult rollbackUnused(@RequestParam("accountId") Long accountId, @RequestParam("id") Long id) {
        return couponUserService.rollbackUnused(accountId, id);
    }

    @RequestMapping("/used")
    public AjaxResult used(@RequestParam("accountId") Long accountId, @RequestParam("id") Long id) {
        return couponUserService.used(accountId, id);
    }
}
