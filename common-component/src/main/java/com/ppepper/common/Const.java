package com.ppepper.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-06
 */
public class Const {

    public static final List<Class> IGNORE_PARAM_LIST = new ArrayList<>();

    static {
        IGNORE_PARAM_LIST.add(Integer.class);
        IGNORE_PARAM_LIST.add(Long.class);
        IGNORE_PARAM_LIST.add(String.class);
        IGNORE_PARAM_LIST.add(Float.class);
        IGNORE_PARAM_LIST.add(Double.class);
        IGNORE_PARAM_LIST.add(Boolean.class);
    }

    public static final Integer CACHE_ONE_DAY = 60 * 60 * 24;

    public static final Integer CACHE_ONE_HOUR = 60 * 60;


    public static final String USER_ACCESS_TOKEN = "ACCESSTOKEN";

    public static final String USER_REDIS_PREFIX = "USER_SESSION_";

    public static final String ADMIN_ACCESS_TOKEN = "ADMINTOKEN";

    public static final String ADMIN_REDIS_PREFIX = "ADMIN_SESSION_";

}
