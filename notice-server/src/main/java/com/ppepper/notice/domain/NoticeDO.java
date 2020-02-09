package com.ppepper.notice.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@EqualsAndHashCode(callSuper = true)
@TableName("b2c_notice")
@Data
public class NoticeDO extends SuperDO {
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

    private Integer type;
    @TableField(value = "account_id")
    private Long accountId;
    @TableField(value = "read_count")
    private Long readCount;
}
