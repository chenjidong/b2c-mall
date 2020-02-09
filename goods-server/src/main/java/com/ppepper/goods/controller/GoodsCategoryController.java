package com.ppepper.goods.controller;

import com.ppepper.common.dto.SpuCategoryDTO;
import com.ppepper.goods.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@RestController
@RequestMapping(value = "/api/goods/category")
public class GoodsCategoryController {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public SpuCategoryDTO get(@RequestParam("id") Long id) {
        return goodsCategoryService.get(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<SpuCategoryDTO> list() {
        return goodsCategoryService.list();
    }
}
