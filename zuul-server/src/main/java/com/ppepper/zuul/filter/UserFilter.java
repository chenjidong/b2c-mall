package com.ppepper.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ppepper.common.jwt.JwtTokenComponent;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with ChenJiDong
 * Created By 2020-02-06
 * <p>
 * FilterConstants.PRE_TYPE请求被路由前调用
 * FilterConstants.POST_TYPE在ROUTE和ERROR后调用
 * FilterConstants.ROUTE_TYPE请求时调用
 * FilterConstants.ERROR_TYPE请求出现错误时调用
 */
@Component
public class UserFilter extends ZuulFilter {

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String uri = request.getRequestURI();
        String token = request.getHeader(jwtTokenComponent.getHeader());//获取token
        return SecurityUtils.shouldFilter(uri, jwtTokenComponent.getUsernameFromToken(token));
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String token = request.getHeader(jwtTokenComponent.getHeader());//获取token
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(jwtTokenComponent.getUsernameFromToken(token))) {
            RequestContext.getCurrentContext().setSendZuulResponse(false);//不进行路由转发
            RequestContext.getCurrentContext().setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
            RequestContext.getCurrentContext().setResponseBody(JSON.toJSONString(AjaxResult.error("No Authority!")));
        }
        return null;
    }
}
