package com.ppepper.admin.service;

import com.ppepper.admin.mapper.RoleMapper;
import com.ppepper.common.dto.RoleDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public AjaxResult get(Long id) {
        return toAjax(copyProperties(roleMapper.selectById(id), RoleDTO.class));
    }
}
