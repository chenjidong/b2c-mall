package com.ppepper.coupon.mapper;


import com.ppepper.common.dto.CouponDTO;
import com.ppepper.common.mapper.BaseMapper;
import com.ppepper.coupon.domain.CouponDO;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
public interface CouponMapper extends BaseMapper<CouponDO> {

    public Integer decCoupon(Long couponId);

    public List<CouponDTO> getActiveCoupons();
}
