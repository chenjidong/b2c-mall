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
@FeignClient(value = "coupon-service")
public interface CouponFeignClient {

    /**
     * 获取优惠券
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/coupon/get", method = RequestMethod.GET)
    public AjaxResult get(@RequestParam("id") Long id);

    /**
     * 获取用户优惠券 【需登录】
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/coupon/user/get", method = RequestMethod.GET)
    public AjaxResult getByUserId(@RequestParam("id") Long id);


    @RequestMapping(value = "/api/coupon/used", method = RequestMethod.GET)
    public AjaxResult used(@RequestParam("id") Long id);

    @RequestMapping(value = "/api/coupon/rollbackUnused", method = RequestMethod.GET)
    public AjaxResult rollbackUnused(@RequestParam("accountId") Long accountId, @RequestParam("id") Long id);
}
