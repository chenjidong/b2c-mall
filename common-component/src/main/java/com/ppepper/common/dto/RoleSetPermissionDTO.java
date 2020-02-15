package com.ppepper.common.dto;

import lombok.Data;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@Data
public class RoleSetPermissionDTO {

    private Long roleId;

    private List<String> permissions;

}
