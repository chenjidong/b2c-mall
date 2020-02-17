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
@RequestMapping(value = "/sys/goods")
public class GoodsSysController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/get")
    public AjaxResult findGoodsById(@RequestParam("id") Long id) {
        return goodsService.get(id);
    }

    @RequestMapping(value = "/getByIds")
    public AjaxResult getByIds(@RequestParam("ids") Long[] ids) {
        return goodsService.getByIds(ids);
    }

    @RequestMapping(value = "/stock")
    public AjaxResult stock(@RequestParam("id") Long id, @RequestParam("num") Integer num) {
        return goodsService.stock(id, num);
    }

    @RequestMapping(value = "/freezeStock")
    public AjaxResult freezeStock(Long id, Integer num) {
        return goodsService.freezeStock(id, num);
    }

    @RequestMapping(value = "/rollbackStock")
    public AjaxResult rollbackStock(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        return goodsService.rollbackStock(skuId, num);
    }

}
