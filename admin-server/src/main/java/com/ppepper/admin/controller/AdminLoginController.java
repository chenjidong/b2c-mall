package com.ppepper.admin.controller;

import com.ppepper.common.controller.BaseController;
import com.ppepper.common.model.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@RestController
@RequestMapping("/admin/login")
public class AdminLoginController extends BaseController {

    @RequestMapping("/logout")
    public AjaxResult logout() {

        return error("账户或密码不正确");
    }
}
