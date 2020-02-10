package com.ppepper.goods.service;

import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
public interface GoodsCategoryService {

    /**
     * 分页缓存 前缀
     */
    String CACHE_SPU_CATEGORY_PAGE_PREFIX = "CACHE_SPU_CATEGORY_PAGE_";

    public AjaxResult get(Long id);

    public AjaxResult list();
}
