package com.ppepper.common.feign;

import com.ppepper.common.model.AjaxResult;
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

    /**
     * 商品详情
     * @param id 商品id
     * @return json
     */
    @RequestMapping(value = "/api/goods/get", method = RequestMethod.GET)
    public AjaxResult get(@RequestParam("id") Long id);

    @RequestMapping(value = "/api/goods/getByIds", method = RequestMethod.GET)
    public AjaxResult getByIds(@RequestParam("ids")Long[] ids);

}
