package com.ppepper.account.controller;

import com.ppepper.account.service.CollectService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-11
 */
@RestController
@RequestMapping("/api/account/collect")
public class CollectController {


    @Autowired
    private CollectService collectService;

    @RequestMapping("/get")
    public AjaxResult get(@RequestParam("id") Long id) {
        return collectService.get(JwtTokenUtils.getCurrentAccountIdByToken(), id);
    }

    @RequestMapping("/save")
    public AjaxResult save(@RequestParam("id") Long id, @RequestParam("type") Integer type, @RequestParam("isDel") Boolean isDel) {
        if (isDel == null)
            isDel = false;

        return collectService.save(JwtTokenUtils.getCurrentAccountIdByToken(), id, type, isDel);
    }
}
