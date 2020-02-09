package com.ppepper.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 * 统一处理敏感字段 如 password  idCard 等
 */
@Component
public class ResultFilter extends ZuulFilter {
    private static final Logger logger = Logger.getLogger(ResultFilter.class.getSimpleName());

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            String body = StreamUtils.copyToString(context.getResponseDataStream(), Charset.forName("UTF-8"));
            if (!StringUtils.isEmpty(body)) {
                logger.info(body);
                // TODO: 2020-02-09 Gson 解析处理
                context.setResponseBody(body);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
