package com.ppepper.common.utils;

import org.springframework.util.AntPathMatcher;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
public class PathUtils {

    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * URLs 配置 路径
     *
     * @param pattern 表达式  例： /api//?r
     * @param path    路径
     * @return boolean
     */
    public static boolean isMatch(String pattern, String path) {
        return antPathMatcher.match(pattern, path);
    }
}
