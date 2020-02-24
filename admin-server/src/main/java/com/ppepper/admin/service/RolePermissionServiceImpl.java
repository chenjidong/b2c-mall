package com.ppepper.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ppepper.admin.domain.RolePermissionDO;
import com.ppepper.admin.mapper.RolePermissionMapper;
import com.ppepper.common.dto.RolePermissionDTO;
import com.ppepper.common.dto.RoleSetPermissionDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.*;

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

    @Override
    public AjaxResult list(Long roleId) {
        List<RolePermissionDO> rolePermissionDOList =
                permissionMapper.selectList(
                        new EntityWrapper<RolePermissionDO>()
                                .eq("role_id", roleId));

        List<RolePermissionDO> allPermission =
                permissionMapper.selectList(
                        new EntityWrapper<RolePermissionDO>().eq("role_id", 0).notIn("permission", "*"));//默认权限

        List<RolePermissionDTO> rolePermissionDTOS = copyListProperties(allPermission, RolePermissionDTO.class);
        List<String> allPermissions = new ArrayList<>();
        allPermission.forEach(item -> {
            if (!"*".equalsIgnoreCase(item.getPermission()))
                allPermissions.add(item.getPermission());
        });

        Map<String, Object> map = new HashMap<>();
        List<String> permissionPoint = new ArrayList<>();
        for (RolePermissionDO permissionDO : rolePermissionDOList) {
            if ("*".equals(permissionDO.getPermission())) {
                //若为超级管理员，则直接push所有权限进去
                permissionPoint.addAll(allPermissions);
                break;
            } else {
                permissionPoint.add(permissionDO.getPermission());
            }
        }
        map.put("systemPermissions", rolePermissionDTOS);
        map.put("assignedPermissions", permissionPoint);
        return toAjax(map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public AjaxResult update(RoleSetPermissionDTO roleSetPermissionDTO) {
        Long roleId = roleSetPermissionDTO.getRoleId();
        if (roleId == null) {
            return error("角色不存在");
        }
        Object point = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try {
            permissionMapper.delete(new EntityWrapper<RolePermissionDO>().eq("role_id", roleId));
            //构建插入
            List<String> permissions = roleSetPermissionDTO.getPermissions();
            if (!CollectionUtils.isEmpty(permissions)) {
                Date now = new Date();
                for (String permission : permissions) {
                    RolePermissionDO origin = new RolePermissionDO();
                    origin.setPermission(permission);
                    origin.setRoleId(0L);
                    RolePermissionDO per = permissionMapper.selectOne(origin);
                    if (per != null) {
                        RolePermissionDO rolePermissionDO = new RolePermissionDO();
                        rolePermissionDO.setRoleId(roleId);
                        rolePermissionDO.setDeleted(0);
                        rolePermissionDO.setPermission(per.getPermission());
                        rolePermissionDO.setLabel(per.getLabel());
                        rolePermissionDO.setGmtCreate(now);
                        rolePermissionDO.setGmtUpdate(now);
                        permissionMapper.insert(rolePermissionDO);
                    }
                }
            }
            return success();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(point);
        }

        return error("修改失败");
    }

    @Override
    public List<RolePermissionDO> list() {
        return permissionMapper.selectList(new EntityWrapper<RolePermissionDO>().eq("role_id", 0).notIn("permission", "*"));
    }
}
