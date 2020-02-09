package com.ppepper.account.controller;

import com.ppepper.account.service.AccountService;
import com.ppepper.common.controller.BaseController;
import com.ppepper.common.dto.AccountDTO;
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

    @RequestMapping("/getByPhone")
    public AjaxResult getByPhone(@RequestParam("phone") String phone) {
        return success(accountService.getByPhone(phone));
    }

    @RequestMapping("/getByUsername")
    public AccountDTO getByUsername(@RequestParam("phone") String phone) {
        return accountService.getByPhone(phone);
    }
}
