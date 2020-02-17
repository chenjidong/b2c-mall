package com.ppepper.common.feign.client;

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


    @RequestMapping(value = "/api/order/get", method = RequestMethod.GET)
    public AjaxResult get(@RequestParam("id") Long id);


    @RequestMapping(value = "/sys/order/getByOrderNo", method = RequestMethod.GET)
    public AjaxResult getByOrderNo(@RequestParam("orderNo") String orderNo);

    @RequestMapping(value = "/sys/order/setStatus", method = RequestMethod.GET)
    public AjaxResult setStatus(@RequestParam("orderNo") String orderNo, @RequestParam("status") Integer status);
}
