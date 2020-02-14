package com.ppepper.account.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
public interface AccountService {

    public AjaxResult getByPhone(String phone);

    public AjaxResult getByUsername(String username);

    public AjaxResult get(Long id);
}
