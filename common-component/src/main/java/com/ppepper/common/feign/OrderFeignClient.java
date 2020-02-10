package com.ppepper.common.feign;

import com.ppepper.common.model.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with ChenJiDong
 * Created By 2020-02-10
 */
@FeignClient(value = "order-service")
public interface OrderFeignClient {

    /**
     * 订单详情 【需登录】
     *
     * @param id 订单id
     * @return
     */
    @RequestMapping(value = "/api/order/get", method = RequestMethod.GET)
    public AjaxResult get(@RequestParam("id") Long id);
}
