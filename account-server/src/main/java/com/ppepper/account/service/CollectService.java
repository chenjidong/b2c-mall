package com.ppepper.account.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-11
 */
public interface CollectService {


    public AjaxResult get(Long accountId, Long id);

    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, Integer type);


    public AjaxResult save(Long accountId, Long id, Integer type, Boolean isDel);

}
