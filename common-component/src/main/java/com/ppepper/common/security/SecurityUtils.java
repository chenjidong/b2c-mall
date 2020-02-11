package com.ppepper.common.security;

import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.common.utils.PathUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 * 权限验证工具
 */
public class SecurityUtils {

    public static final String PREFIX_ROLE = "ROLE_";//前缀 由 security 配置 更改时需 同步
    public static final String NO_PREFIX_ROLE_USER = "USER";
    public static final String NO_PREFIX_ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = PREFIX_ROLE + NO_PREFIX_ROLE_USER;
    public static final String ROLE_ADMIN = PREFIX_ROLE + NO_PREFIX_ROLE_ADMIN;


    public static final HashMap<String, List<String>> PERMISSION_PATTERN_MAP = new HashMap<String, List<String>>() {{
        put(NO_PREFIX_ROLE_USER, new ArrayList<String>() {{
            add("/api/sso/user/**");
            add("/api/order/**");
            add("/api/coupon/**");
            add("/api/notice/**");
        }});
        put(NO_PREFIX_ROLE_ADMIN, new ArrayList<String>() {{
            add("/admin/**");
        }});
    }};

    /**
     * 检查 zuul 是否需要权限拦截
     *
     * @param uri   请求uri
     * @param token
     * @return
     */
    public static boolean shouldFilter(String uri, String token) {
        JwtTokenUtils.JwtSubjectBean bean = JwtTokenUtils.getSubjectByToken(token);
        if (bean == null) {
            for (String role : PERMISSION_PATTERN_MAP.keySet()) {
                for (String pattern : PERMISSION_PATTERN_MAP.get(role)) {
                    if (PathUtils.isMatch(pattern, uri)) {
                        return true;
                    }
                }
            }
            return false;
        }

        try {
            String role = getRole(bean.getRoles()[0]);
            for (String pattern : PERMISSION_PATTERN_MAP.get(role)) {
                if (PathUtils.isMatch(pattern, uri))
                    return true;
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            //有可能是假的token
            for (String role : PERMISSION_PATTERN_MAP.keySet()) {
                for (String pattern : PERMISSION_PATTERN_MAP.get(role)) {
                    if (PathUtils.isMatch(pattern, uri)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    /**
     * 获取不带前缀 ‘ROLE_’ 角色名称
     */
    public static String getRole(String role) {
        String name;
        try {
            name = role.replace(SecurityUtils.PREFIX_ROLE, "").trim();
        } catch (Exception e) {
            return null;
        }
        return name;
    }
}
