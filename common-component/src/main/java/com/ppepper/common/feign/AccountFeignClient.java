package com.ppepper.common.feign;

import com.fasterxml.jackson.databind.util.Named;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 * 账号服务
 */
@FeignClient(name = "account-service")
public interface AccountFeignClient {

    /**
     * 根据手机号获取用户信息
     *
     * @param phone 登录手机号
     * @return json
     */
    @RequestMapping("/api/account/getByUsername")
    public AjaxResult getByUsername(@RequestParam("phone") String phone);

    @RequestMapping("/api/account/get")
    public AjaxResult get(@RequestParam("id") Long id);


    /**
     * 获取收藏详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/api/account/collect/get")
    public AjaxResult getCollect(@RequestParam("id") Long id);


    @RequestMapping("/api/account/cart/list")
    public AjaxResult getCartList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);


    @RequestMapping("/api/account/address/get")
    public AjaxResult getAddress(@RequestParam("id") Long id);

    @RequestMapping("/api/account/cart/clean")
    public AjaxResult cleanCart();
}
