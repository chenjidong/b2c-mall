package com.ppepper.goods.controller;


import com.ppepper.common.controller.BaseController;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@RestController
@RequestMapping(value = "/api/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/get")
    public AjaxResult findGoodsById(@RequestParam("id") Long id) {
        String phone = JwtTokenUtils.getRealUsername();
        System.out.println("findGoodsById() id:" + id + " phone:" + phone);
        return goodsService.get(id);
    }

    @RequestMapping(value = "/list")
    public AjaxResult list(@RequestParam("pageNo") Integer pageNo,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam("categoryId") Long categoryId,
                           @RequestParam("orderBy") String orderBy,
                           @RequestParam("isAsc") Boolean isAsc,
                           @RequestParam("title") String title) {
        return goodsService.list(pageNo, pageSize, categoryId, orderBy, isAsc, title);
    }
}
