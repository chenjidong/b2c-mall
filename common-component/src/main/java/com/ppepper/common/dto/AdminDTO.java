package com.ppepper.common.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminDTO extends SuperDTO {

    private String username;
    private String avatarUrl;
    @TableField("role_ids")
    private String roleIds;

    private Integer status;

    private Long accountId;
}
