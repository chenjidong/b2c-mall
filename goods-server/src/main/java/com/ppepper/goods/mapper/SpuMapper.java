package com.ppepper.goods.mapper;


import com.ppepper.common.mapper.BaseMapper;
import com.ppepper.goods.domain.SpuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public interface SpuMapper extends BaseMapper<SpuDO> {

    /**
     * 仅可传入叶子类目
     *
     * @param categoryId
     * @return
     */
    public List<SpuDO> getSpuTitleByCategoryId(Long categoryId);

    /**
     * 增加Spu累计销量
     *
     * @param spuId
     * @param delta
     * @return
     */
    public Integer incSales(@Param("spuId") Long spuId, @Param("delta") Integer delta);

    public List<SpuDO> getSpuTitleAll();

}
