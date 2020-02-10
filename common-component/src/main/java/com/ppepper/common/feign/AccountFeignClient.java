package com.ppepper.common.feign;

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

    /**
     * 根据手机号获取用户信息
     *
     * @param phone 登录手机号
     * @return json
     */
    @RequestMapping("/api/account/getByUsername")
    public AjaxResult getByUsername(@RequestParam("phone") String phone);

}
