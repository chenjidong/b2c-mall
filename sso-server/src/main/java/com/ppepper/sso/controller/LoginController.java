package com.ppepper.sso.controller;

import com.ppepper.sso.component.JwtTokenUtil;
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
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public String login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(phone,pwd);
        Authentication authentication  = authenticationManager.authenticate(token);
        User userDetails = (User) authentication.getPrincipal();
        if (userDetails != null) {
            return jwtTokenUtil.generateToken(userDetails);
        }
        return null;
    }
}
