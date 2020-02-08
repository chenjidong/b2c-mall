package com.ppepper.sso.controller;

import com.ppepper.common.component.JwtTokenComponent;
import com.ppepper.common.controller.BaseController;
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
@RequestMapping("/sso")
public class LoginController extends BaseController {

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public AjaxResult login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(phone, pwd);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        User userDetails = (User) authentication.getPrincipal();
        if (userDetails != null) {
            String token = jwtTokenComponent.generateToken(userDetails.getUsername());
            return success(token);
        }
        return error("账号或密码不正确！");
    }
}
