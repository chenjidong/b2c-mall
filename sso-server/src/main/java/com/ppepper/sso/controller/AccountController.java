package com.ppepper.sso.controller;

import com.ppepper.common.dto.AccountDTO;
import com.ppepper.sso.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/getByPhone")
    public AccountDTO getByPhone(@RequestParam("phone") String phone) {
        return accountService.getByPhone(phone);
    }
}
