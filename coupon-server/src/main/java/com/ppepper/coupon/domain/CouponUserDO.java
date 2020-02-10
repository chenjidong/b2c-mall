package com.ppepper.coupon.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("b2c_coupon_user")
public class CouponUserDO extends SuperDO {

    @TableField("account_id")
    private Long accountId;

    @TableField("coupon_id")
    private Long couponId;

    @TableField("order_id")
    private Long orderId;

    @TableField("gmt_used")
    private Date gmtUsed;

    @TableField("gmt_start")
    private Date gmtStart;

    @TableField("gmt_end")
    private Date gmtEnd;

}
