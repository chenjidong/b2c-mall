package com.ppepper.sso.controller;

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


    @RequestMapping("/login")
    public String login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {

        return phone + pwd;
    }
}
