package com.ppepper.sso.controller;

import com.ppepper.common.model.AjaxResult;
import com.ppepper.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public AjaxResult get(@RequestParam("id") Long id) {
        return AjaxResult.success(userService.get(id));
    }
}
