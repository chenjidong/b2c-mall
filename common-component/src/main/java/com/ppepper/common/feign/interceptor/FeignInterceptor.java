package com.ppepper.common.feign.interceptor;

import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.common.utils.ServletUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with ChenJiDong
 * Created By 2020-02-12
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        try {
            Map<String, String> headers = getHeaders();
            boolean hasAuthorizationKey = false;
            for (String headerName : headers.keySet()) {
                if (StringUtils.pathEquals(headerName, JwtTokenUtils.HEADER)) {
                    hasAuthorizationKey = true;
                }
                template.header(headerName, headers.get(headerName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getHeaders() {
        HttpServletRequest request = ServletUtils.getRequest();
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
