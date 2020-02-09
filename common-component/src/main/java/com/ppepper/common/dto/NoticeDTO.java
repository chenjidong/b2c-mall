package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NoticeDTO extends SuperDTO {
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 首图
     */
    private String image;
    /**
     * 描述
     */
    private String description;
    /**
     * 富文本详情
     */
    private String content;

    private Date gmtUpdate;

    private Date gmtCreate;

    private Integer type;
    private Long accountId;
    private Long readCount;
}
