package com.ppepper.sso.component;

import java.util.List;
import java.util.Map;

/**
 * Created with ChenJiDong
 * Created By 2020-02-24
 */
public interface HttpSecurityUrlMatchersService {


    public Map<String, List<String>> onLoadPermission();

    /**
     * @return 0 账户 1 后台管理员
     */
    public int type();
}
