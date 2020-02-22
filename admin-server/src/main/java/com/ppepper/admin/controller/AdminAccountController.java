package com.ppepper.admin.controller;

import com.ppepper.common.controller.BaseController;
import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.feign.AccountSysFeignService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-22
 */
@RestController
@RequestMapping("/admin/account")
public class AdminAccountController extends BaseController {

    @Autowired
    private AccountSysFeignService accountSysFeignService;

    @RequestMapping("/list")
    public Page<AccountDTO> list(Integer page, Integer limit, String nickname,
                                 String orderBy,
                                 Boolean isAsc,
                                 Long id,
                                 Integer status) {
        return accountSysFeignService.list(page, limit, nickname, orderBy, isAsc, id, status);
    }

    @RequestMapping("/updateStatus")
    public AjaxResult updateStatus(Long id, Integer status) {
        return accountSysFeignService.updateStatus(id, status);
    }

    @RequestMapping(value = "/createByAdmin",method = RequestMethod.POST)
    public AjaxResult createByAdmin(@RequestBody AccountDTO accountDTO) {
        return accountSysFeignService.createByAdmin(accountDTO);
    }

    @RequestMapping("/delete")
    public AjaxResult delete(Long id) {
        return accountSysFeignService.delete(id);
    }
}
