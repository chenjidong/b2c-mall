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
    public AjaxResult getByUsername(@RequestParam("username") String username) {
        return accountService.getByUsername(username);
    }

    @RequestMapping("/getByPhone")
    public AjaxResult getByPhone(@RequestParam("phone") String phone) {
        return accountService.getByPhone(phone);
    }

    @RequestMapping("/get")
    public AjaxResult get(@RequestParam("id") Long id) {
        return accountService.get(id);
    }


    @RequestMapping("/sendCode")
    public AjaxResult sendCode(@RequestParam("phone") String phone) {
        return accountService.sendCode(phone);
    }

    @RequestMapping("/create")
    public AjaxResult create(@RequestParam("phone") String phone, String password, String code) {
        return accountService.create(phone, password, code);
    }

}
