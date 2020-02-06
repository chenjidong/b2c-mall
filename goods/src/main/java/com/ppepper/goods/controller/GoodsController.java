package com.ppepper.goods.controller;


import com.ppepper.goods.model.Goods;
import com.ppepper.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/{id}")
    public Goods findGoodsById(@PathVariable("id") Long id) {
        System.out.println("findGoodsById() id:"+id);
        return goodsService.findGoodsById(id);
    }
}
