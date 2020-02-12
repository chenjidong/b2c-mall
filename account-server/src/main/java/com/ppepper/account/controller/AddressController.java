package com.ppepper.account.controller;

import com.ppepper.account.service.AddressService;
import com.ppepper.common.controller.BaseController;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@RestController
@RequestMapping("/api/account/address")
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;


    @RequestMapping("/get")
    public AjaxResult get(@RequestParam("id") Long id) {
        return addressService.get(JwtTokenUtils.getCurrentAccountIdByToken(), id);
    }
}
