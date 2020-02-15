package com.ppepper.admin.service;

import com.ppepper.common.dto.AdminDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
public interface AdminService {

    public AjaxResult loginByUsername(String username, String password, String verifyCode);

    AjaxResult get(Long id);

    AjaxResult delete(Long id);


    AjaxResult create(AdminDTO adminDTO);

    AjaxResult getByRoleId(Long roleId);

    public Page<AdminDTO> list(Integer pageNo, Integer pageSize, String username, String orderBy, Boolean isAsc);

    public AjaxResult changePassword(Long id, String oldPassword, String newPassword, String newPassword2);

}
