package com.ppepper.goods.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 * 商品sku
 */
@Data
@TableName("b2c_sku")
public class SkuDO extends SuperDO {

    @TableField("spu_id")
    private Long spuId;

    @TableField("bar_code")
    private String barCode;

    /**
     * SKU显示文字
     */
    private String title;

    private String img;

    @TableField("original_price")
    private Integer originalPrice;

    private Integer price;

    @TableField("vip_price")
    private Integer vipPrice;

    private Integer stock;

    @TableField("freeze_stock")
    private Integer freezeStock;

}
