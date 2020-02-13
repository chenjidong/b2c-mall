package com.ppepper.coupon.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("b2c_coupon")
@AllArgsConstructor
@NoArgsConstructor
public class CouponDO extends SuperDO {

    private String title;

    private Integer type;

    private String description;

    private Integer total;//数量

    private Integer surplus;//剩余

    private Integer limit;//限制使用数量 按订单算

    private Integer discount;//折扣 根据类型使用

    private Integer min;//最低消费

    private Integer status;

    @TableField("category_id")
    private Long categoryId; //指定spu 分类才能领劵 null 表示全场通用

    private Integer days;// 相对领劵天数 即创建时间 加上 天数

    @TableField("gmt_start")
    private Date gmtStart;

    @TableField("gmt_end")
    private Date gmtEnd;

    @TableField("spu_id")
    private Long spuId; //指定商品能领取优惠券

    @TableField("shop_id")
    private Long shopId; //指定商铺能领取优惠券

}
