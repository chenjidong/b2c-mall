package com.ppepper.admin.controller;

import com.ppepper.admin.service.RolePermissionService;
import com.ppepper.common.dto.RoleSetPermissionDTO;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-15
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/permission")
public class AdminPermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @RequestMapping("/list")
    public AjaxResult list(Long roleId) {
        return rolePermissionService.list(roleId);
    }

    @RequestMapping("/update")
    public AjaxResult update(@RequestBody RoleSetPermissionDTO roleSetPermissionDTO) {
        return rolePermissionService.update(roleSetPermissionDTO);
    }
}
