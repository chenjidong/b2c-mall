package com.ppepper.admin.service;

import com.ppepper.common.dto.RoleSetPermissionDTO;
import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
public interface RolePermissionService {

    AjaxResult get(Long id);

    AjaxResult getByRoleId(Long roleId);

    AjaxResult list(Long roleId);

    AjaxResult update(RoleSetPermissionDTO roleSetPermissionDTO);

}
