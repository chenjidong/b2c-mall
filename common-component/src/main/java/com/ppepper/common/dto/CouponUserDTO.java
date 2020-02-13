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
public class CouponUserDTO extends SuperDTO {

    private Long accountId;

    private Long couponId;

    private Long orderId;

    private Date gmtUsed;

    private Date gmtStart;

    private Date gmtEnd;

    private CouponDTO couponDTO;
}
