package com.ppepper.order.service;


import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.model.Page;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public interface OrderService {

    public OrderDTO get(Long id);

    public Page<OrderDTO> list(Integer pageNo, Integer pageSize, Integer status, Long accountId);
}
