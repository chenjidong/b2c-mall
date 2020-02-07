package com.ppepper.goods.mapper;


import com.ppepper.common.mapper.BaseMapper;
import com.ppepper.goods.domain.SpuSkuDO;
import com.ppepper.common.dto.SpuSkuDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public interface SpuSkuMapper extends BaseMapper<SpuSkuDO> {

    public SpuSkuDTO getSkuDTOById(Long skuId);

    public Integer decSkuStock(@Param("skuId") Long skuId, @Param("stock") Integer stock);

    /**
     * 删除SPUID
     *
     * @param spuId
     * @return
     */
    public List<Long> getSkuIds(@Param("spuId") Long spuId);

    List<Long> selectSkuIdsBySpuIds(@Param("ids") List<Long> ids);
}
