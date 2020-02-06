package com.ppepper.order.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Data
@TableName("b2c_order_sku")
public class OrderSkuDO extends SuperDO {

    @TableField("sku_id")
    private Long skuId;

    @TableField("spu_id")
    private Long spuId;

    @TableField("order_id")
    private Long orderId;


    @TableField("order_no")
    private String orderNo;

    @TableField("spu_title")
    private String spuTitle;

    private String title;

    @TableField("bar_code")
    private String barCode;

    private Integer num;

    @TableField("original_price")
    private Integer originalPrice;

    private Integer price;

    private String img;

    private String unit;

}
