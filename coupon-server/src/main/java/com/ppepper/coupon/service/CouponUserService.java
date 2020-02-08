package com.ppepper.coupon.service;

import com.ppepper.common.dto.CouponUserDTO;
import com.ppepper.common.model.Page;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
public interface CouponUserService {

    public Page<CouponUserDTO> getByUserId(Long userId, Integer pageNo,Integer pageSize);
}
