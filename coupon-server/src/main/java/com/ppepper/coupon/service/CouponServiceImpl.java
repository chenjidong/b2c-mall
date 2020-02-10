package com.ppepper.coupon.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.dto.CouponDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.coupon.domain.CouponDO;
import com.ppepper.coupon.mapper.CouponMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@Service
public class CouponServiceImpl extends BaseServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public AjaxResult get(Long id) {
        return success("", copyProperties(couponMapper.selectById(id), CouponDTO.class));
    }

    @Override
    public AjaxResult list(Integer pageNo, Integer pageSize, Long categoryId, Integer status, Integer type, String orderBy, Boolean isAsc, String title) {
        Wrapper<CouponDO> wrapper = new EntityWrapper<>();
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
        List<CouponDO> couponDOList = couponMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        if (couponDOList != null && !couponDOList.isEmpty()) {
            return success(copyListProperties(couponDOList, CouponDTO.class));
        }
        return error();
    }
}
