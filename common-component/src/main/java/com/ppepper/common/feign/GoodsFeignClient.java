package com.ppepper.common.feign;

import com.ppepper.common.dto.SpuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 * feign 调用服务
 */
@FeignClient(name = "goods-service")
public interface GoodsFeignClient {

    @RequestMapping(value = "/goods/get", method = RequestMethod.GET)
    public SpuDTO get(@RequestParam("id") Long id);
}
