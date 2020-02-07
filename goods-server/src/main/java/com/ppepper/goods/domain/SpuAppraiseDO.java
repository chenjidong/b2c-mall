package com.ppepper.goods.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 * 商品评价
 */
@EqualsAndHashCode(callSuper = true)
@TableName("b2c_spu_appraise")
@Data
public class SpuAppraiseDO extends SuperDO {

    @TableField("spu_id")
    private Long spuId;
    @TableField("sku_id")
    private Long skuId;
    @TableField("order_id")
    private Long orderId;
    @TableField("user_id")
    private Long userId;

    //评论内容
    private String content;
    //评论星数
    private Integer score;


}
