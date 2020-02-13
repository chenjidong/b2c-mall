package com.ppepper.payment.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
public interface PaymentService {

    public AjaxResult getByPayNo(Long accountId, String payNo);

    public AjaxResult getByOrderNo(Long accountId, String orderNo);

    public AjaxResult create(Long accountId, String orderNo, String channel, Integer payType);
}
