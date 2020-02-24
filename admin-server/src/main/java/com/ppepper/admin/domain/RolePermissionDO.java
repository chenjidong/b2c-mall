package com.ppepper.admin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("b2c_role_permission")
public class RolePermissionDO extends SuperDO {

    @TableField("role_id")
    private Long roleId;//0 表示系统内置

    private String permission;

    private Integer deleted;

    private String label;

    /**
     * 路径规则参照 spring security 语法 /admin/**
     */
    private String url;
}
