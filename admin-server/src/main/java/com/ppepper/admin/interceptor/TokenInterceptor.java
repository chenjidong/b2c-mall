package com.ppepper.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;


@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(JwtTokenUtils.HEADER);//获取token
        if (!StringUtils.isEmpty(token) && !JwtTokenUtils.isTokenExpired(token)) {//判断token是否为空
            return true;
        }
        return parseFail(response);
    }

    private static boolean parseFail(HttpServletResponse response) {
        response.setStatus(HttpURLConnection.HTTP_UNAUTHORIZED);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().print(JSON.toJSONString(AjaxResult.error("授权失败！")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
