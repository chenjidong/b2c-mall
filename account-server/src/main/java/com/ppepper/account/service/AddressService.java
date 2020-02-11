package com.ppepper.account.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-11
 */
public interface AddressService {


    public AjaxResult get(Long accountId, Long id);

    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, String phone);

    public AjaxResult add(Long accountId, String province, String city, String county, String address, String phone, String consignee, Boolean isDefault);

    public AjaxResult del(Long accountId, Long id);

    public AjaxResult setDefault(Long accountId, Long id, Boolean isDefault);

}
