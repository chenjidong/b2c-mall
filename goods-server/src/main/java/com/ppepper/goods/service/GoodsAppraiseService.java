package com.ppepper.goods.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
public interface GoodsAppraiseService {

    /**
     * 分页缓存 前缀
     */
    String CACHE_SPU_APPRAISE_PAGE_PREFIX = "CACHE_SPU_APPRAISE_PAGE_";

    public AjaxResult get(Long accountId, Long appraiseId);

    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, String orderBy, Boolean isAsc, String keyword, Integer score);
}
