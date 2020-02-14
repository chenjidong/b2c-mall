package com.ppepper.admin.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
public interface AdminService {

    AjaxResult get(Long id);

    AjaxResult getByRoleId(Long roleId);

}
