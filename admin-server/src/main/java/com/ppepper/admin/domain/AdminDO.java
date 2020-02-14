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
@TableName("b2c_admin")
@Data
public class AdminDO extends SuperDO {
    /**
     * 管理员名
     */
    private String username;

    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 管理员角色 JSON 数据
     */
    @TableField("role_ids")
    private String roleIds;

    /**
     * 管理员状态
     */
    private Integer status;
    @TableField("account_id")
    private Long accountId;
}
