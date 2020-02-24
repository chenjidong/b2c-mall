package com.ppepper.sso.component;

import com.ppepper.common.security.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created with ChenJiDong
 * Created By 2020-02-24
 */
@Component
public class AccountHttpSecurityUrlMatchersService implements HttpSecurityUrlMatchersService {


    @Override
    public Map<String, List<String>> onLoadPermission() {
        return SecurityUtils.ACCOUNT_PERMISSION_PATTERN_MAP;
    }

    @Override
    public int type() {
        return 0;
    }
}
