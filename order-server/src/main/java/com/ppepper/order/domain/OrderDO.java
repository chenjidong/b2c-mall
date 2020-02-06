package com.ppepper.order.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Data
@TableName("b2c_order")
public class OrderDO extends SuperDO {

    private String channel;

    @TableField("order_no")
    private String orderNo;

    @TableField("user_id")
    private Long userId;

    private Integer status;

    @TableField("sku_original_total_price")
    private Integer skuOriginalTotalPrice;

    @TableField("sku_total_price")
    private Integer skuTotalPrice;

    @TableField("freight_price")
    private Integer freightPrice;

    @TableField("coupon_price")
    private Integer couponPrice;

    @TableField("coupon_id")
    private Long couponId;

    @TableField("group_shop_id")
    private Long groupShopId;

    @TableField("actual_price")
    private Integer actualPrice;

    @TableField("pay_price")
    private Integer payPrice;

    @TableField("pay_id")
    private String payId;

    @TableField("pay_channel")
    private String payChannel;

    @TableField("gmt_pay")
    private Date gmtPay;

    @TableField("ship_no")
    private String shipNo;

    @TableField("ship_code")
    private String shipCode;

    private String province;

    private String city;

    private String county;

    private String address;

    private String phone;

    private String consignee;

    private String mono;

    @TableField("gmt_ship")
    private Date gmtShip;

    @TableField("gmt_confirm")
    private Date gmtConfirm;


}
