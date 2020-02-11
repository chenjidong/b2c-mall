package com.ppepper.account.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-11
 */
public interface CartService {


    public AjaxResult get(Long accountId, Long id);

    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize);

    public AjaxResult add(Long accountId, Long skuId, Integer num);

    public AjaxResult del(Long accountId, Long skuId);

}
