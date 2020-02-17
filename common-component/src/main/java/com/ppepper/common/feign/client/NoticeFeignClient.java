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
@FeignClient(value = "notice-service")
public interface NoticeFeignClient {

    /**
     * 获取用户通知 【需登录】
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/notice/user/get", method = RequestMethod.GET)
    public AjaxResult getByUserId(@RequestParam("id") Long id);
}
