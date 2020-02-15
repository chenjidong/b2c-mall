package com.ppepper.sso.controller;

import com.ppepper.common.controller.BaseController;
import com.ppepper.common.feign.AccountFeignService;
import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.common.model.AjaxResult;
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
    private AccountFeignService accountFeignService;

    @RequestMapping("/login")
    public AjaxResult login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(phone, pwd);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        User userDetails = (User) authentication.getPrincipal();
        if (userDetails != null) {
            String token = JwtTokenUtils.generateToken(userDetails.getUsername());
            return success("登录成功", token);
        }
        return error("账号或密码不正确！");
    }

    @RequestMapping("/sendCode")
    public AjaxResult sendCode(String phone) {
        return accountFeignService.sendCode(phone);
    }

    @RequestMapping("/create")
    public AjaxResult create(String phone, String password, String code) {
        return accountFeignService.create(phone, password, code);
    }
}
