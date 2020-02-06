package com.ppepper.common.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 * 数据库实体基类
 */
@Data
public class SuperDO {

    private Long id;

    @TableField("gmt_update")
    private Date gmtUpdate;

    @TableField("gmt_create")
    private Date gmtCreate;
}
