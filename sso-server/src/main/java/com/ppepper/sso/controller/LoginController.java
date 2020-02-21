package com.ppepper.sso.controller;

import com.ppepper.common.controller.BaseController;
import com.ppepper.common.feign.AccountSysFeignService;
import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@RestController
@RequestMapping("/api/sso")
public class LoginController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountSysFeignService accountSysFeignService;

    @RequestMapping("/login")
    public AjaxResult login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {
        try {
            String password = MD5Util.md5(pwd, phone);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(phone, password);
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            User userDetails = (User) authentication.getPrincipal();
            if (userDetails != null) {
                String token = JwtTokenUtils.generateToken(userDetails.getUsername());
                return success("登录成功", token);
            }
        } catch (Exception e) {
            return error(e.getMessage());
        }

        return error("账号或密码不正确！");
    }

    @RequestMapping("/sendCode")
    public AjaxResult sendCode(String phone) {
        return accountSysFeignService.sendCode(phone);
    }

    @RequestMapping("/create")
    public AjaxResult create(String phone, String password, String code) {
        return accountSysFeignService.create(phone, password, code);
    }
}
