package com.ppepper.goods.service;

import com.ppepper.common.dto.SpuCategoryDTO;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
public interface GoodsCategoryService {

    public SpuCategoryDTO get(Long id);

    public List<SpuCategoryDTO> list();
}
