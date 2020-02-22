package com.ppepper.account.controller;

import com.ppepper.account.service.AccountService;
import com.ppepper.common.controller.BaseController;
import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@RestController
@RequestMapping("/sys/account")
public class AccountSysController extends BaseController {

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

    @RequestMapping("/list")
    public Page<AccountDTO> list(Integer page,
                                 Integer limit,
                                 String nickname,
                                 String orderBy,
                                 Boolean isAsc,
                                 Long id,
                                 Integer status) {
        return accountService.list(page, limit, nickname, orderBy, isAsc, id, status);
    }


    @RequestMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestParam("id") Long id, Integer status) {
        return accountService.updateStatus(id, status);
    }

    @RequestMapping("/createByAdmin")
    public AjaxResult createByAdmin(@RequestParam("id") Long id,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("password") String password,
                                    @RequestParam("status") Integer status,
                                    @RequestParam("nickname") String nickname
    ) {
        return accountService.createByAdmin(id, phone, password, status, nickname);
    }

    @RequestMapping("/delete")
    public AjaxResult delete(@RequestParam("id") Long id) {
        return accountService.delete(id);
    }
}
