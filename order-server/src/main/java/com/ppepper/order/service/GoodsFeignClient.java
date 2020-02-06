package com.ppepper.order.service;

import com.ppepper.order.model.GoodsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@FeignClient(name = "goods-service")
public interface GoodsFeignClient {

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public GoodsDTO findGoodsById(@PathVariable("id") Long id);
}
