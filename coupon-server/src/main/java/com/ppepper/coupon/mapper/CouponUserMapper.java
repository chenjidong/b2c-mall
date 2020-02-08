package com.ppepper.coupon.mapper;


import com.ppepper.common.dto.CouponUserDTO;
import com.ppepper.common.mapper.BaseMapper;
import com.ppepper.coupon.domain.CouponUserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by rize on 2019/7/4.
 */
public interface CouponUserMapper extends BaseMapper<CouponUserDO> {

    public List<CouponUserDTO> getUserCoupons(Long userId);

    public CouponUserDTO getUserCouponById(@Param("userCouponId") Long userCouponId, @Param("userId") Long userId);

}
