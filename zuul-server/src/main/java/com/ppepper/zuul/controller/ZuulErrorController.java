package com.ppepper.zuul.controller;

import com.ppepper.common.model.AjaxResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 * 覆盖 zuul controller 捕捉异常
 */
@RestController
public class ZuulErrorController implements ErrorController {

    @RequestMapping
    public AjaxResult error(HttpServletRequest request, HttpServletResponse response) {
        return AjaxResult.error("系统繁忙！请稍后重试！");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
