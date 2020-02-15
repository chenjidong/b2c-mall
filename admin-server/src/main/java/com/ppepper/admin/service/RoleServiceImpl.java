package com.ppepper.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.admin.domain.RoleDO;
import com.ppepper.admin.mapper.RoleMapper;
import com.ppepper.common.dto.RoleDTO;
import com.ppepper.common.enums.RoleStatusType;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;
import com.ppepper.common.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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


    @Override
    public AjaxResult options() {
        List<RoleDO> roleDOS = roleMapper.selectList(new EntityWrapper<>());
        List<Map<String, Object>> list = new LinkedList<>();
        roleDOS.forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("value", item.getId());
            map.put("label", item.getName());
            list.add(map);
        });
        return toAjax(list);
    }

    @Override
    public Page<RoleDTO> list(Integer page, Integer limit, String name, String orderBy, Boolean isAsc) {
        Wrapper<RoleDO> wrapper = new EntityWrapper<>();
        if (name != null)
            wrapper.like("name", name);
        if (orderBy != null && isAsc != null)
            wrapper.orderBy(orderBy, isAsc);

        List<RoleDO> list = roleMapper.selectPage(new RowBounds((page - 1) * limit, limit), wrapper);
        List<RoleDTO> roleDTOS = copyListProperties(list, RoleDTO.class);

        int count = roleMapper.selectCount(wrapper);
        return new Page<>(roleDTOS, page, limit, count);
    }

    @Override
    public AjaxResult create(RoleDTO roleDTO) {
        if (roleDTO == null)
            return error("新增失败");
        if (roleDTO.getId() == null) {
            RoleDO roleDO = new RoleDO();
            Date now = new Date();
            roleDO.setStatus(RoleStatusType.ACTIVE.getCode());
            roleDO.setGmtUpdate(now);
            roleDO.setName(roleDTO.getName());
            roleDO.setDesc(roleDTO.getDesc());
            roleDO.setGmtCreate(now);
            roleMapper.insert(roleDO);
            return toAjax(roleDO);
        } else {
            RoleDO roleDO = roleMapper.selectById(roleDTO.getId());
            if (roleDO == null)
                return error("角色不存在");

            roleDO.setName(roleDTO.getName());
            roleDO.setDesc(roleDTO.getDesc());
            roleDO.setGmtUpdate(new Date());
            roleMapper.updateById(roleDO);
            return toAjax(roleDO);

        }
    }

    @Override
    public AjaxResult delete(Long id) {
        return toAjax(roleMapper.deleteById(id));
    }
}
