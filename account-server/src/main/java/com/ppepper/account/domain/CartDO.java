package com.ppepper.account.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("b2c_cart")
public class CartDO extends SuperDO {

    @TableField("sku_id")
    private Long skuId;

    @TableField("account_id")
    private Long accountId;

    private Integer num;

}
