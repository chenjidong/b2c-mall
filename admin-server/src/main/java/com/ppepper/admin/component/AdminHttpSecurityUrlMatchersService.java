package com.ppepper.admin.component;

import com.ppepper.admin.domain.RolePermissionDO;
import com.ppepper.admin.service.RolePermissionService;
import com.ppepper.common.security.SecurityUtils;
import com.ppepper.sso.component.HttpSecurityUrlMatchersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with ChenJiDong
 * Created By 2020-02-24
 */
@Component
@Primary
public class AdminHttpSecurityUrlMatchersService implements HttpSecurityUrlMatchersService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public Map<String, List<String>> onLoadPermission() {

        if (SecurityUtils.ADMIN_PERMISSION_PATTERN_MAP.isEmpty()) {
            List<RolePermissionDO> list = rolePermissionService.list();
            Map<String, List<String>> map = new HashMap<>();
            list.forEach(item -> {
                map.put(item.getPermission(), new ArrayList<String>() {{
                    add(item.getUrl());
                }});
            });
            SecurityUtils.ADMIN_PERMISSION_PATTERN_MAP = map;
        }
        return SecurityUtils.ADMIN_PERMISSION_PATTERN_MAP;
    }

    @Override
    public int type() {
        return 1;
    }
}
