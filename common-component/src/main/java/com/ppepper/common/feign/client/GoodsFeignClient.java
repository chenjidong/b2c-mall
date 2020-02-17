package com.ppepper.common.feign.client;

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


    @RequestMapping(value = "/sys/goods/get", method = RequestMethod.GET)
    public AjaxResult get(@RequestParam("id") Long id);

    @RequestMapping(value = "/sys/goods/getByIds", method = RequestMethod.GET)
    public AjaxResult getByIds(@RequestParam("ids") Long[] ids);

    /**
     * spu 只包含 当前skuid 的
     */
    @RequestMapping(value = "/sys/goods/getBySkuIds", method = RequestMethod.GET)
    public AjaxResult getBySkuIds(@RequestParam("skuIds") Long[] skuIds);


    @RequestMapping(value = "/sys/goods/stock")
    public AjaxResult stock(@RequestParam("id") Long id, @RequestParam("num") Integer num);

    @RequestMapping(value = "/sys/goods/freezeStock")
    public AjaxResult freezeStock(@RequestParam("id") Long id, @RequestParam("num") Integer num);

    @RequestMapping(value = "/sys/goods/rollbackStock")
    public AjaxResult rollbackStock(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num);


    @RequestMapping(value = "/api/goods/get", method = RequestMethod.GET)
    public AjaxResult getGoods(@RequestParam("id") Long id);


}
