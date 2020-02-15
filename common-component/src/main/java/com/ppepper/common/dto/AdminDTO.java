package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminDTO extends SuperDTO {

    private String username;
    private String avatarUrl;

    private Integer status;

    private Long accountId;

    /**
     * 管理员登录密码
     */
    private String password;

    private String phone;

    private String lastLoginIp;

    private String gmtLastLogin;

    private List<String> roles;

    private List<Long> roleIds;

    private List<String> perms;
}
