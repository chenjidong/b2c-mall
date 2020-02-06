package com.ppepper.goods.service;


import com.ppepper.common.model.Page;
import com.ppepper.common.dto.SpuDTO;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public interface GoodsService {

    /**
     * SPU 分页缓存
     */
    public static final String CA_SPU_PAGE_PREFIX = "CA_SPU_PAGE_";


    public Page<SpuDTO> getGoodsPage(
            Integer pageNo,
            Integer pageSize,
            Long categoryId,
            String orderBy,
            Boolean isAsc,
            String title);

    public SpuDTO getGoods(
            Long spuId);

}
