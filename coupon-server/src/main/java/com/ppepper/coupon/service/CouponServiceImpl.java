package com.ppepper.coupon.service;

import com.ppepper.common.dto.CouponDTO;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.coupon.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@Service
public class CouponServiceImpl extends BaseServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;


    @Override
    public CouponDTO get(Long id) {
        return copyProperties(couponMapper.selectById(id), CouponDTO.class);
    }
}
