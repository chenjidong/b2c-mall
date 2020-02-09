package com.ppepper.common.exception;

import com.ppepper.common.model.AjaxResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 * 统一处理springboot 异常返回
 * tips： 或继承  {@link org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController}  处理  只能存在一个
 */
@RestController
public class ServiceErrorController implements ErrorController {

    @RequestMapping
    public AjaxResult error(HttpServletRequest request, HttpServletResponse response) {
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        return AjaxResult.error("系统繁忙！请稍后重试！");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
