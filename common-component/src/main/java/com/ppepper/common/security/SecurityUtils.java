package com.ppepper.common.security;

import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.common.utils.PathUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 * 权限验证工具
 */
public class SecurityUtils {

    public static final String PREFIX_ROLE = "ROLE_";//前缀 由 security 配置 更改时需 同步
    public static final String NO_PREFIX_ROLE_USER = "USER";
    public static final String NO_PREFIX_ROLE_ADMIN = "ADMIN";
    public static final String NO_PREFIX_ROLE_SHOP = "SHOP";
    public static final String ROLE_USER = PREFIX_ROLE + NO_PREFIX_ROLE_USER;
    public static final String ROLE_ADMIN = PREFIX_ROLE + NO_PREFIX_ROLE_ADMIN;
    public static final String ROLE_SHOP = PREFIX_ROLE + NO_PREFIX_ROLE_SHOP;


    public static final HashMap<String, List<String>> ACCOUNT_PERMISSION_PATTERN_MAP = new HashMap<String, List<String>>() {{
        put(NO_PREFIX_ROLE_USER, new ArrayList<String>() {{
            add("/api/account/**");
            add("/api/order/**");
            add("/api/coupon/**");
            add("/api/notice/**");
            add("/api/payment/**");
        }});
        put(NO_PREFIX_ROLE_SHOP, new ArrayList<String>() {{
            add("/api/shop/**");
        }});
    }};

    public static Map<String, List<String>> ADMIN_PERMISSION_PATTERN_MAP = new HashMap<>();

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
            for (String role : ACCOUNT_PERMISSION_PATTERN_MAP.keySet()) {
                for (String pattern : ACCOUNT_PERMISSION_PATTERN_MAP.get(role)) {
                    if (PathUtils.isMatch(pattern, uri)) {
                        return true;
                    }
                }
            }
            return false;
        }

        try {
            String role = getRole(bean.getRoles()[0]);
            for (String pattern : ACCOUNT_PERMISSION_PATTERN_MAP.get(role)) {
                if (PathUtils.isMatch(pattern, uri))
                    return true;
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            //有可能是假的token
            for (String role : ACCOUNT_PERMISSION_PATTERN_MAP.keySet()) {
                for (String pattern : ACCOUNT_PERMISSION_PATTERN_MAP.get(role)) {
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
