package com.ppepper.sso.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 * 账户
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("b2c_account")
public class AccountDO extends SuperDO {

    private String phone;

    private String username;

    private String password;

    @TableField("login_type")
    private Integer loginType;

    private String nickname;

    private Integer status;

    @TableField("gmt_last_login")
    private Date gmtLastLogin;

    @TableField("last_login_ip")
    private String lastLoginIp;
}
