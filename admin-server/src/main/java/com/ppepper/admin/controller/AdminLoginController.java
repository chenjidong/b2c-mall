package com.ppepper.admin.controller;

import com.ppepper.common.controller.BaseController;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@RestController
@RequestMapping("/admin/login")
@CrossOrigin
public class AdminLoginController extends BaseController {


    @RequestMapping("/loginByUsername")
    public AjaxResult loginByUsername(String username, String password,String verifyCode) {
        if ("admin".equalsIgnoreCase(username) && "1234567".equalsIgnoreCase(password)) {
            String token = JwtTokenUtils.generateToken(JwtTokenUtils.generateSubject("admin", 1L, "admin"));
            return toAjax(token);
        }
        return error("账户或密码不正确");
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
