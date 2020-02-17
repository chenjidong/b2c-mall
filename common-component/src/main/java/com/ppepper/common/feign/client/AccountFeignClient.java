package com.ppepper.common.feign.client;

import com.ppepper.common.model.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 * 账号服务
 */
@FeignClient(name = "account-service")
public interface AccountFeignClient {


    @RequestMapping("/sys/account/getByUsername")
    public AjaxResult getByUsername(@RequestParam("username") String username);

    @RequestMapping("/sys/account/getByPhone")
    public AjaxResult getByPhone(@RequestParam("phone") String phone);

    @RequestMapping("/sys/account/get")
    public AjaxResult get(@RequestParam("id") Long id);


    @RequestMapping("/sys/account/sendCode")
    public AjaxResult sendCode(@RequestParam("phone") String phone);

    @RequestMapping("/sys/account/create")
    public AjaxResult create(@RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("code") String code);


    @RequestMapping("/api/account/collect/get")
    public AjaxResult getCollect(@RequestParam("id") Long id);


    @RequestMapping("/api/account/cart/list")
    public AjaxResult getCartList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);


    @RequestMapping("/api/account/address/get")
    public AjaxResult getAddress(@RequestParam("id") Long id);

    @RequestMapping("/api/account/cart/clean")
    public AjaxResult cleanCart();

}
