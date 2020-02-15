package com.ppepper.admin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.admin.domain.AdminDO;
import com.ppepper.admin.domain.RoleDO;
import com.ppepper.admin.domain.RolePermissionDO;
import com.ppepper.admin.mapper.AdminMapper;
import com.ppepper.admin.mapper.RoleMapper;
import com.ppepper.admin.mapper.RolePermissionMapper;
import com.ppepper.common.dto.AdminDTO;
import com.ppepper.common.enums.AdminStatusType;
import com.ppepper.common.enums.RoleStatusType;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;
import com.ppepper.common.service.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public AjaxResult get(Long id) {
        AdminDO adminDO = new AdminDO();
        adminDO.setId(id);
        adminDO = adminMapper.selectOne(adminDO);
        AdminDTO adminDTO = copyProperties(adminDO, AdminDTO.class);
        if (adminDTO != null) {
            //获取角色
            List<Long> ids = JSONObject.parseArray(adminDO.getRoleIds(), Long.class);
            List<RoleDO> roleDOList = roleMapper.selectList(
                    new EntityWrapper<RoleDO>()
                            .in("id", ids)
                            .eq("status", RoleStatusType.ACTIVE.getCode()));

            List<String> roleNames = new LinkedList<>();
            roleDOList.forEach(item -> {
                roleNames.add(item.getName());
            });

            adminDTO.setRoles(roleNames);

            //获取权限
            List<RolePermissionDO> rolePermissionDOList = rolePermissionMapper.selectList(
                    new EntityWrapper<RolePermissionDO>()
                            .in("role_id", ids)
                            .eq("deleted", 0));
            List<String> permissionNames = new LinkedList<>();
            rolePermissionDOList.forEach(item -> {
                permissionNames.add(item.getPermission());
            });

            adminDTO.setPerms(permissionNames);
        }
        return toAjax(adminDTO);
    }

    @Override
    public AjaxResult delete(Long id) {
        return toAjax(adminMapper.deleteById(id));
    }

    @Override
    public AjaxResult create(AdminDTO adminDTO) {
        if (adminDTO == null)
            return error("创建失败");

        if (adminDTO.getUsername() == null || adminDTO.getPassword() == null)
            return error("创建失败");

        if (adminDTO.getId() == null) {
            AdminDO adminDO = new AdminDO();
            adminDO.setUsername(adminDTO.getUsername());
            adminDO = adminMapper.selectOne(adminDO);
            if (adminDO != null)
                return error("用户已存在");
            adminDO = new AdminDO();
            adminDO.setUsername(adminDTO.getUsername());
            adminDO.setPhone(adminDTO.getPhone());
            adminDO.setRoleIds(JSON.toJSONString(adminDTO.getRoleIds()));
            adminDO.setStatus(AdminStatusType.ACTIVE.getCode());
            adminDO.setPassword(adminDTO.getPassword());
            adminDO.setGmtUpdate(new Date());
            adminDO.setGmtCreate(new Date());
            int count = adminMapper.insert(adminDO);
            return toAjax(count);
        } else {
            AdminDO adminDO = adminMapper.selectById(adminDTO.getId());
            if (adminDO == null)
                return error("用户不存在");
            adminDO.setRoleIds(JSON.toJSONString(adminDTO.getRoleIds()));
            adminDO.setPassword(adminDTO.getPassword());
            adminDO.setPhone(adminDTO.getPhone());
            adminDO.setGmtUpdate(new Date());
            int count = adminMapper.updateById(adminDO);
            return toAjax(count);
        }
    }

    @Override
    public AjaxResult getByRoleId(Long roleId) {
        Wrapper<AdminDO> wrapper = new EntityWrapper<>();
        wrapper.like("role_ids", "[" + roleId + "]");
        List<AdminDO> list = adminMapper.selectList(wrapper);
        List<AdminDTO> adminDTOS = copyListProperties(list, AdminDTO.class);
        return toAjax(adminDTOS);
    }

    @Override
    public Page<AdminDTO> list(Integer pageNo, Integer pageSize, String username, String orderBy, Boolean isAsc) {
        Wrapper<AdminDO> wrapper = new EntityWrapper<>();
        if (username != null)
            wrapper.like("username", username);
        if (orderBy != null && isAsc != null)
            wrapper.orderBy(orderBy, isAsc);

        List<AdminDO> list = adminMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        int count = adminMapper.selectCount(wrapper);
        List<AdminDTO> adminDTOS = new ArrayList<>();
        if (list != null) {
            list.forEach(adminDO -> {
                AdminDTO adminDTO = copyProperties(adminDO, AdminDTO.class);
                //获取角色
                List<Long> ids = JSONObject.parseArray(adminDO.getRoleIds(), Long.class);
                List<RoleDO> roleDOList = roleMapper.selectList(
                        new EntityWrapper<RoleDO>()
                                .in("id", ids)
                                .eq("status", RoleStatusType.ACTIVE.getCode()));

                List<String> roleNames = new LinkedList<>();
                roleDOList.forEach(item -> {
                    roleNames.add(item.getName());
                });

                adminDTO.setRoles(roleNames);

                //获取权限
                List<RolePermissionDO> rolePermissionDOList = rolePermissionMapper.selectList(
                        new EntityWrapper<RolePermissionDO>()
                                .in("role_id", ids)
                                .eq("deleted", 0));
                List<String> permissionNames = new LinkedList<>();
                rolePermissionDOList.forEach(item -> {
                    permissionNames.add(item.getPermission());
                });

                adminDTO.setPerms(permissionNames);
                adminDTO.setRoleIds(ids);
                adminDTOS.add(adminDTO);
            });
        }
        return new Page<>(adminDTOS, pageNo, pageSize, count);
    }

    @Override
    public AjaxResult changePassword(Long id, String oldPassword, String newPassword, String newPassword2) {
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(newPassword2))
            return error("请输入新旧密码");
        if (!StringUtils.equals(newPassword, newPassword2)) {
            return error("两次密码不一致");
        }
        AdminDO adminDO = adminMapper.selectById(id);
        if (adminDO == null)
            return error("非法的操作");
        if (!StringUtils.equals(adminDO.getPassword(), oldPassword)) {
            return error("密码错误");
        }
        adminDO.setPassword(newPassword);
        adminDO.setGmtUpdate(new Date());
        return toAjax(adminMapper.updateById(adminDO));
    }
}
