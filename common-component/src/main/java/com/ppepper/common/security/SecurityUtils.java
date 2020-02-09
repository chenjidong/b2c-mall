package com.ppepper.common.security;

import com.ppepper.common.utils.PathUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
public class SecurityUtils {

    public static final String NO_PREFIX_ROLE_USER = "USER";
    public static final String NO_PREFIX_ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";


    public static final HashMap<String, List<String>> PERMISSION_PATTERN_MAP = new HashMap<String, List<String>>() {{
        put(NO_PREFIX_ROLE_USER, new ArrayList<String>() {{
            add("/api/sso/user/**");
            add("/api/order/**");
            add("/api/coupon/**");
        }});
        put(NO_PREFIX_ROLE_ADMIN, new ArrayList<String>() {{
            add("/admin/**");
        }});
    }};

    /**
     * 检查 zuul 是否需要权限拦截
     *
     * @param uri 请求路径
     * @return boolean
     */
    public static boolean shouldFilter(String uri, String subject) {
        if (StringUtils.isEmpty(subject))
            return true;

        if (!StringUtils.isEmpty(uri)) {
            try {
                String role = getRole(subject);
                for (String pattern : PERMISSION_PATTERN_MAP.get(role)) {
                    if (PathUtils.isMatch(pattern, uri))
                        return true;
                }
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                return true;//匹配失败 也要拦截 在run() 中返回401
            }

        }
        return false;
    }

    public static String getRole(String subject) {
        String name;
        try {
            name = subject.split("\\.")[1].replace("ROLE_", "").trim();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }

        return name;
    }

    public static String getRealUsername(String username) {
        String name;
        try {
            name = username.split("\\.")[0].trim();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
        return name;
    }

    public static String getUsername(String username, String... roles) {
        StringBuilder stringBuilder = new StringBuilder();
        if (roles.length == 0)
            stringBuilder.append(roles[0]);
        else {
            for (int i = 0; i < roles.length; i++) {
                stringBuilder.append(roles[i]);
                if (i > (roles.length - 1)) {
                    stringBuilder.append(",");
                }
            }
        }
        return username + "." + stringBuilder.toString();
    }
}
