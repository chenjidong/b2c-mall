package com.ppepper.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;

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
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            RequestContext.getCurrentContext().setSendZuulResponse(false);//不进行路由转发
            RequestContext.getCurrentContext().setResponseStatusCode(HttpURLConnection.HTTP_OK);
            RequestContext.getCurrentContext().setResponseBody("{\"error\":\"invalid token\"}");
        }
        return null;
    }
}
