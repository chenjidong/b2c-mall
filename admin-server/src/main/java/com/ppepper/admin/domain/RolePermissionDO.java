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
    private Long roleId;

    private String permission;

    private Integer deleted;

}
