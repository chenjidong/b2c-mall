package com.ppepper.goods.service;


import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public interface GoodsService {

    /**
     * SPU 分页缓存 前缀
     */
    String CACHE_SPU_PAGE_PREFIX = "CACHE_SPU_PAGE_";


    public AjaxResult list(
            Integer pageNo,
            Integer pageSize,
            Long categoryId,
            String orderBy,
            Boolean isAsc,
            String title);

    public AjaxResult get(
            Long spuId);

}
