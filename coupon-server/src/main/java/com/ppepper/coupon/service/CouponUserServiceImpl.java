package com.ppepper.coupon.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.dto.CouponDTO;
import com.ppepper.common.dto.CouponUserDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.coupon.domain.CouponDO;
import com.ppepper.coupon.domain.CouponUserDO;
import com.ppepper.coupon.mapper.CouponMapper;
import com.ppepper.coupon.mapper.CouponUserMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@Service
public class CouponUserServiceImpl extends BaseServiceImpl implements CouponUserService {
    @Autowired
    private CouponUserMapper couponUserMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public AjaxResult get(Long accountId, Long id) {
        CouponUserDO couponUserDO = new CouponUserDO();
        couponUserDO.setAccountId(accountId);
        couponUserDO.setId(id);
        CouponUserDTO couponUserDTO = copyProperties(couponUserMapper.selectOne(couponUserDO), CouponUserDTO.class);
        if (couponUserDTO != null) {
            CouponDO couponDO = couponMapper.selectById(couponUserDTO.getCouponId());
            if (couponDO == null)
                return error("优惠券不存在");
            couponUserDTO.setCouponDTO(copyProperties(couponDO, CouponDTO.class));
        }
        return toAjax(couponUserDTO);
    }

    @Override
    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, Long categoryId, Integer status, Integer type, String orderBy, Boolean isAsc, String title) {
        Wrapper<CouponUserDO> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(title))
            wrapper.like("title", title);
        if (orderBy != null && isAsc != null)
            wrapper.orderBy(orderBy, isAsc);
        if (type != null)
            wrapper.eq("type", type);
        if (status != null)
            wrapper.eq("status", status);
        if (categoryId != null)
            wrapper.eq("category_id", categoryId);
        wrapper.eq("account_id", accountId);
        List<CouponUserDO> couponDOList = couponUserMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        if (couponDOList != null && !couponDOList.isEmpty()) {
            return success(copyListProperties(couponDOList, CouponUserDTO.class));
        }
        return error();
    }

    @Override
    public AjaxResult used(Long accountId, Long id) {
        CouponUserDO couponUserDO = new CouponUserDO();
        couponUserDO.setAccountId(accountId);
        couponUserDO.setId(id);
        couponUserDO = couponUserMapper.selectOne(couponUserDO);
        if (couponUserDO != null) {
            couponUserDO.setGmtUsed(new Date());
            couponUserDO.setGmtUpdate(new Date());
            int count = couponUserMapper.updateById(couponUserDO);
            return toAjax(count);
        }
        return error("操作失败");
    }
}
