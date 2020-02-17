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

    /**
     * 商品详情
     *
     * @param id 商品id
     * @return json
     */
    @RequestMapping(value = "/api/goods/get", method = RequestMethod.GET)
    public AjaxResult get(@RequestParam("id") Long id);

    @RequestMapping(value = "/api/goods/getByIds", method = RequestMethod.GET)
    public AjaxResult getByIds(@RequestParam("ids") Long[] ids);


    /**
     * spu 只包含 当前skuid 的
     *
     * @param skuIds
     * @return
     */
    @RequestMapping(value = "/api/goods/getBySkuIds", method = RequestMethod.GET)
    public AjaxResult getBySkuIds(@RequestParam("skuIds") Long[] skuIds);


    @RequestMapping(value = "/api/goods/stock")
    public AjaxResult stock(@RequestParam("id") Long id, @RequestParam("num") Integer num);

    @RequestMapping(value = "/api/goods/freezeStock")
    public AjaxResult freezeStock(@RequestParam("id") Long id, @RequestParam("num") Integer num);

    @RequestMapping(value = "/api/goods/rollbackStock")
    public AjaxResult rollbackStock(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num);

}
