package com.ppepper.sso.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("b2c_user")
public class UserDO extends SuperDO {

    private String phone;

    private String password;

    @TableField("login_type")
    private Integer loginType;

    @TableField("open_id")
    private String openId;

    private String nickname;

    @TableField("avatar_url")
    private String avatarUrl;

    private Integer level;

    private Date birthday;

    private Integer gender;

    @TableField("gmt_last_login")
    private Date gmtLastLogin;

    @TableField("last_login_ip")
    private String lastLoginIp;

    private Integer status;

}
