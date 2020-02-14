package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends SuperDTO {

    private String name;

    private String desc;

    private Integer status;

}
