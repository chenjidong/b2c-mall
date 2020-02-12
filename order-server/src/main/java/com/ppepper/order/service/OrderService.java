package com.ppepper.order.service;


import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public interface OrderService {

    public AjaxResult get(Long id, Long accountId);

    public AjaxResult list(Integer pageNo, Integer pageSize, Integer status, Long accountId, String orderBy, Boolean isAsc);


    public AjaxResult createByCart(Long accountId,Long addressId,Long couponId,String channel);
}
