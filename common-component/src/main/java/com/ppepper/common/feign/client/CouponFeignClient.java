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
@FeignClient(value = "coupon-service")
public interface CouponFeignClient {

    @RequestMapping(value = "/sys/coupon/used", method = RequestMethod.GET)
    public AjaxResult used(@RequestParam("accountId") Long accountId, @RequestParam("id") Long id);

    @RequestMapping(value = "/sys/coupon/rollbackUnused", method = RequestMethod.GET)
    public AjaxResult rollbackUnused(@RequestParam("accountId") Long accountId, @RequestParam("id") Long id);


    @RequestMapping(value = "/api/coupon/user/get", method = RequestMethod.GET)
    public AjaxResult getUserCoupon(@RequestParam("id") Long id);


}
