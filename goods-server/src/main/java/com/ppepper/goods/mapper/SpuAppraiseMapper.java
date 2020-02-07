package com.ppepper.goods.mapper;

import com.ppepper.common.dto.SpuAppraiseDTO;
import com.ppepper.common.mapper.BaseMapper;
import com.ppepper.goods.domain.SpuAppraiseDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public interface SpuAppraiseMapper extends BaseMapper<SpuAppraiseDO> {

    //根据用户id，分页获取所有评价
    public List<SpuAppraiseDTO> selectUserAllAppraise(@Param("userId") Long userId, @Param("offset") Integer offset, @Param("size") Integer size);

    //根据商品spu_id，分页获取所有评价
    public List<SpuAppraiseDTO> selectSpuAllAppraise(@Param("spuId") Long spuId, @Param("offset") Integer offset, @Param("size") Integer size);

    //根据评价ID，查询评价
    public SpuAppraiseDTO selectOneById(@Param("appraiseId") Long appraiseId);

    //根据传入条件，查询
    public List<SpuAppraiseDTO> selectAppraiseCondition(@Param("id") Long id, @Param("userName") String userName, @Param("spuName") String spuName, @Param("orderId") Long orderId, @Param("score") Integer score, @Param("content") String content, @Param("offset") Integer offset, @Param("limit") Integer limit);

    //根据传入条件，查询符合条件总数
    public Integer countAppraiseCondition(@Param("id") Long id, @Param("userName") String userName, @Param("spuName") String spuName, @Param("orderId") Long orderId, @Param("score") Integer score, @Param("content") String content);
}
