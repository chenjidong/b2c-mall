package com.ppepper.goods.controller;

import com.ppepper.common.dto.SpuAppraiseDTO;
import com.ppepper.common.model.Page;
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
@RequestMapping("/goods/appraise")
public class GoodsAppraiseController {

    @Autowired
    private GoodsAppraiseService goodsAppraiseService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public SpuAppraiseDTO get(@RequestParam("id") Long id) {
        return goodsAppraiseService.selectOneById(id);
    }

    @RequestMapping(value = "/selectUserAllAppraise", method = RequestMethod.GET)
    public Page<SpuAppraiseDTO> selectUserAllAppraise(@RequestParam("userId") Long userId, @RequestParam("offset") Integer offset, @RequestParam("size") Integer size) {
        return goodsAppraiseService.selectUserAllAppraise(userId, offset, size);
    }

    @RequestMapping(value = "/selectSpuAllAppraise", method = RequestMethod.GET)
    public Page<SpuAppraiseDTO> selectSpuAllAppraise(@RequestParam("spuId") Long spuId, @RequestParam("offset") Integer offset, @RequestParam("size") Integer size) {
        return goodsAppraiseService.selectSpuAllAppraise(spuId, offset, size);
    }
}
