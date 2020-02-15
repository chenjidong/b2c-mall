package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionDTO extends SuperDTO {
    private String label;
    private String permission;
    private List<RolePermissionDTO> children = new ArrayList<>();
}
