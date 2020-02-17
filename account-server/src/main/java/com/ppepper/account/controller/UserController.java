package com.ppepper.account.controller;

import com.ppepper.account.service.UserService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@RestController
@RequestMapping("/api/account/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public AjaxResult get() {
        return userService.get(JwtTokenUtils.getCurrentAccountIdByToken());
    }
}
