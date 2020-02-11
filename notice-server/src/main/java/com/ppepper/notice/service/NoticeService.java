package com.ppepper.notice.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
public interface NoticeService {

    public AjaxResult get(
            Long accountId,
            Long id);

    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, Integer type, String orderBy, Boolean isAsc, String title);
}
