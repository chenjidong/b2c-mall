package com.ppepper.admin.controller;

import com.ppepper.admin.service.AdminService;
import com.ppepper.common.controller.BaseController;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@RestController
@RequestMapping("/admin/login")
public class AdminLoginController extends BaseController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/loginByUsername")
    public AjaxResult loginByUsername(String username, String password, String verifyCode) {
        return adminService.loginByUsername(username, password, verifyCode);
    }

    @RequestMapping("/sendLoginMsg")
    public AjaxResult sendLoginMsg(String phone) {

        return error("账户或密码不正确");
    }

    @RequestMapping("/logout")
    public AjaxResult logout() {

        return error("账户或密码不正确");
    }
}
