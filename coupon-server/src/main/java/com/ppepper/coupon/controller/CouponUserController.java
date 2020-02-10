package com.ppepper.coupon.controller;

import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.feign.AccountFeignService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
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
@RequestMapping("/api/coupon/user")
public class CouponUserController {

    @Autowired
    private CouponUserService couponUserService;

    @Autowired
    private AccountFeignService accountFeignService;

    @RequestMapping("/get")
    public AjaxResult get(@RequestParam("id") Long id) {
        AccountDTO accountDTO = accountFeignService.getByUsername(JwtTokenUtils.getRealUsername());
        Long accountId = accountDTO == null ? 0L : accountDTO.getId();
        return couponUserService.get(accountId, id);
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
        AccountDTO accountDTO = accountFeignService.getByUsername(JwtTokenUtils.getRealUsername());
        Long accountId = accountDTO == null ? 0L : accountDTO.getId();
        return couponUserService.list(accountId, pageNo, pageSize, categoryId, status, type, orderBy, isAsc, title);
    }
}
