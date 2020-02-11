package com.ppepper.account.controller;

import com.ppepper.account.service.AccountService;
import com.ppepper.common.controller.BaseController;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@RestController
@RequestMapping("/api/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/getByUsername")
    public AjaxResult getByUsername(@RequestParam("phone") String phone) {
        return accountService.getByPhone(phone);
    }
}