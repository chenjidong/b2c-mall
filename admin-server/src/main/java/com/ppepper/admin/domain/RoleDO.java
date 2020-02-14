package com.ppepper.admin.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-14
 */
@EqualsAndHashCode(callSuper = true)
@TableName("b2c_role")
@Data
public class RoleDO extends SuperDO {

    private String name;

    private String desc;

    private Integer status;

}
