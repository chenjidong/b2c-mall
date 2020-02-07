package com.ppepper.goods.service;

import com.ppepper.common.dto.SpuAppraiseDTO;
import com.ppepper.common.model.Page;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
public interface GoodsAppraiseService {

    public SpuAppraiseDTO selectOneById(Long appraiseId);

    public Page<SpuAppraiseDTO> selectUserAllAppraise(Long userId, Integer offset, Integer size);

    public Page<SpuAppraiseDTO> selectSpuAllAppraise(Long spuId, Integer offset, Integer size);
}
