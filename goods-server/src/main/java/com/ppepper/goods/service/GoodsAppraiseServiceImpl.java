package com.ppepper.goods.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ppepper.common.dto.SpuAppraiseDTO;
import com.ppepper.common.model.Page;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.goods.domain.SpuAppraiseDO;
import com.ppepper.goods.mapper.SpuAppraiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@Service
public class GoodsAppraiseServiceImpl extends BaseServiceImpl implements GoodsAppraiseService {
    @Autowired
    private SpuAppraiseMapper spuAppraiseMapper;

    @Override
    public SpuAppraiseDTO selectOneById(Long appraiseId) {
        return copyProperties(spuAppraiseMapper.selectOneById(appraiseId), SpuAppraiseDTO.class);
    }

    @Override
    public Page<SpuAppraiseDTO> selectUserAllAppraise(Long userId, Integer offset, Integer size) {
        Integer count = spuAppraiseMapper.selectCount(new EntityWrapper<SpuAppraiseDO>().eq("user_id", userId));
        List<SpuAppraiseDTO> appraiseResponseDTOS = spuAppraiseMapper.selectUserAllAppraise(userId, size * (offset - 1), size);

        return new Page<>(appraiseResponseDTOS, offset, size, count);
    }

    @Override
    public Page<SpuAppraiseDTO> selectSpuAllAppraise(Long spuId, Integer offset, Integer size) {
        Integer count = spuAppraiseMapper.selectCount(new EntityWrapper<SpuAppraiseDO>().eq("spu_id", spuId));
        Integer offset1 = size * (offset - 1);
        List<SpuAppraiseDTO> appraiseResponseDTOS = spuAppraiseMapper.selectSpuAllAppraise(spuId, offset1, size);

        return new Page<>(appraiseResponseDTOS, offset, size, count);
    }
}
