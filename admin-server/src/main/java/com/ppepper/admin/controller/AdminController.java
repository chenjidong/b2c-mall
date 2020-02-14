package com.ppepper.admin.controller;

import com.ppepper.admin.service.AdminService;
import com.ppepper.common.controller.BaseController;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@RestController
@RequestMapping("/admin/admin")
public class AdminController extends BaseController {


    @Autowired
    private AdminService adminService;

    @RequestMapping("/get")
    public AjaxResult get(Long id) {
        return adminService.get(id);
    }
}
