package com.ppepper.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05、
 * 数据传输实体基类
 */
@Data
public class SuperDTO {

    private Long id;

    private Date gmtUpdate;

    private Date gmtCreate;
}
