package com.ppepper.goods.controller;

import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.goods.service.GoodsAppraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@RestController
@RequestMapping("/api/goods/user/appraise")
public class GoodsAppraiseController {

    @Autowired
    private GoodsAppraiseService goodsAppraiseService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxResult get(@RequestParam("id") Long id) {
        return goodsAppraiseService.get(JwtTokenUtils.getCurrentAccountIdByToken(), id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult list(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                           @RequestParam("orderBy") String orderBy, @RequestParam("isAsc") Boolean isAsc,
                           @RequestParam("keyword") String keyword, @RequestParam("score") Integer score) {

        return goodsAppraiseService.list(JwtTokenUtils.getCurrentAccountIdByToken(), pageNo, pageSize, orderBy, isAsc, keyword, score);
    }
}
