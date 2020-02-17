package com.ppepper.account.controller;

import com.ppepper.account.service.CartService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-11
 */
@RestController
@RequestMapping(value = "/api/account/user/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/get")
    public AjaxResult get(Long id) {
        return cartService.get(JwtTokenUtils.getCurrentAccountIdByToken(), id);
    }

    @RequestMapping("/list")

    public AjaxResult list(Integer pageNo, Integer pageSize) {
        return cartService.list(JwtTokenUtils.getCurrentAccountIdByToken(), pageNo, pageSize);
    }

    @RequestMapping("/add")
    public AjaxResult add(Long accountId, Long skuId, Integer num) {
        return cartService.add(JwtTokenUtils.getCurrentAccountIdByToken(), skuId, num);
    }

    @RequestMapping("/del")
    public AjaxResult del(Long skuId) {
        return cartService.del(JwtTokenUtils.getCurrentAccountIdByToken(), skuId);
    }

    @RequestMapping("/clean")
    public AjaxResult clean() {
        return cartService.clean(JwtTokenUtils.getCurrentAccountIdByToken());
    }
}
