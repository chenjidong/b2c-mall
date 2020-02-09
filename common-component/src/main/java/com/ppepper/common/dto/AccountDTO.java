package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountDTO extends SuperDTO {

    private String phone;

    private String username;

    private Integer loginType;

    private String nickname;

    private Integer status;

    private Date gmtLastLogin;

    private String lastLoginIp;

    private String password;


}
