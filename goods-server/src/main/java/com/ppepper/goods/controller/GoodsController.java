package com.ppepper.goods.controller;


import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.model.Page;
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
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/get")
    public SpuDTO findGoodsById(@RequestParam("id") Long id) {
        System.out.println("findGoodsById() id:" + id);
        return goodsService.getGoods(id);
    }

    @RequestMapping(value = "/list")
    public Page<SpuDTO> findGoodsPage(@RequestParam("pageNo") Integer pageNo,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("categoryId") Long categoryId,
                                      @RequestParam("orderBy") String orderBy,
                                      @RequestParam("isAsc") Boolean isAsc,
                                      @RequestParam("title") String title) {
        return goodsService.getGoodsPage(pageNo, pageSize, categoryId, orderBy, isAsc, title);
    }
}
