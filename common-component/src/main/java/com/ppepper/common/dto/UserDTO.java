package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends SuperDTO {

    private Long accountId;

    private String openId;

    private String nickname;

    private String avatarUrl;

    private Integer level;

    private Date birthday;

    private Integer gender;

    private String platform;
}
