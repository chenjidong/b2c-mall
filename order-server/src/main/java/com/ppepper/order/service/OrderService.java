package com.ppepper.order.service;


import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.order.domain.OrderDO;

import java.util.Date;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public interface OrderService {
    static final String ORDER_STATUS_LOCK = "ORDER_STATUS_LOCK_";

    public AjaxResult get(Long id, Long accountId);

    public AjaxResult list(Integer pageNo, Integer pageSize, Integer status, Long accountId, String orderBy, Boolean isAsc);


    public AjaxResult createByCart(Long accountId, Long addressId, Long couponId, String channel);


    public AjaxResult getByOrderNo(Long accountId, String orderNo);

    public AjaxResult setStatus(Long accountId, String orderNo, Integer status);


    public List<OrderDTO> selectExpireOrderNos(Integer status, Date time);


    public int update(OrderDO orderDO);

}
