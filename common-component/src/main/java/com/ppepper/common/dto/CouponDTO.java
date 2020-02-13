package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CouponDTO extends SuperDTO {

    private String title;

    private Integer type;

    private String description;

    private Integer total;

    private Integer surplus;

    private Integer limit;

    private Integer discount;

    private Integer min;

    private Integer status;

    private Long categoryId;

    private String categoryTitle;

    private Integer days;

    private Integer nowCount;

    private Date gmtStart;

    private Date gmtEnd;

    private Long spuId; //指定商品能领取优惠券

    private Long shopId; //指定商铺能领取优惠券
}
