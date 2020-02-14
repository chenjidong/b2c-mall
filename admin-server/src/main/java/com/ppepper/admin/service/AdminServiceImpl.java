package com.ppepper.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.admin.domain.AdminDO;
import com.ppepper.admin.mapper.AdminMapper;
import com.ppepper.common.dto.AdminDTO;
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
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AjaxResult get(Long id) {
        AdminDO adminDO = new AdminDO();
        adminDO.setId(id);
        adminDO = adminMapper.selectOne(adminDO);
        AdminDTO adminDTO = copyProperties(adminDO, AdminDTO.class);
        return toAjax(adminDTO);
    }

    @Override
    public AjaxResult getByRoleId(Long roleId) {
        Wrapper<AdminDO> wrapper = new EntityWrapper<>();
        wrapper.like("role_ids", "[" + roleId + "]");
        List<AdminDO> list = adminMapper.selectList(wrapper);
        List<AdminDTO> adminDTOS = copyListProperties(list, AdminDTO.class);
        return toAjax(adminDTOS);
    }
}
