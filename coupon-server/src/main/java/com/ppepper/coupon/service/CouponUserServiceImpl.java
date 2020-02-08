package com.ppepper.coupon.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.dto.CouponUserDTO;
import com.ppepper.common.model.Page;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.coupon.domain.CouponUserDO;
import com.ppepper.coupon.mapper.CouponUserMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@Service
public class CouponUserServiceImpl extends BaseServiceImpl implements CouponUserService {
    @Autowired
    private CouponUserMapper couponUserMapper;

    @Override
    public Page<CouponUserDTO> getByUserId(Long userId, Integer pageNo, Integer pageSize) {
        Wrapper<CouponUserDO> wrapper = new EntityWrapper<CouponUserDO>().eq("user_id", userId);
        Integer count = couponUserMapper.selectCount(wrapper);
        List<CouponUserDO> couponUserDO = couponUserMapper.selectPage(new RowBounds(((pageNo - 1) * pageSize), pageSize), wrapper);

        return new Page<>(copyListProperties(couponUserDO, CouponUserDTO.class), pageNo, pageSize, count);
    }
}
