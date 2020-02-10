package com.ppepper.coupon.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
public interface CouponUserService {
    public AjaxResult get(
            Long accountId,
            Long id);

    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, Long categoryId, Integer status, Integer type, String orderBy, Boolean isAsc, String title);
}
