package com.ppepper.admin.controller;

import com.ppepper.admin.service.RoleService;
import com.ppepper.common.dto.RoleDTO;
import com.ppepper.common.dto.RoleSetPermissionDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with ChenJiDong
 * Created By 2020-02-15
 */
@RestController
@RequestMapping("/admin/role")
public class AdminRoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/options")
    public AjaxResult options() {
        return roleService.options();
    }

    @RequestMapping("/list")
    public Page<RoleDTO> list(Integer page, Integer limit, String name, @RequestParam("sort") String orderBy, @RequestParam("order") Boolean isAsc) {
        return roleService.list(page, limit, name, orderBy, isAsc);
    }

    @RequestMapping("/delete")
    public AjaxResult delete(Long id) {
        return roleService.delete(id);
    }

    @RequestMapping("/create")
    public AjaxResult create(@RequestBody RoleDTO roleDTO) {
        return roleService.create(roleDTO);
    }

}
