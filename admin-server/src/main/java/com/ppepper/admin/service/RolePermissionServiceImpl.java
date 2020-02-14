package com.ppepper.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ppepper.admin.domain.RolePermissionDO;
import com.ppepper.admin.mapper.RolePermissionMapper;
import com.ppepper.common.dto.RolePermissionDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionMapper permissionMapper;

    @Override
    public AjaxResult get(Long id) {
        return toAjax(copyProperties(permissionMapper.selectById(id), RolePermissionDTO.class));
    }

    @Override
    public AjaxResult getByRoleId(Long roleId) {
        List<RolePermissionDO> list = permissionMapper.selectList(new EntityWrapper<RolePermissionDO>().eq("role_id", roleId));
        return toAjax(copyListProperties(list, RolePermissionDTO.class));
    }
}
