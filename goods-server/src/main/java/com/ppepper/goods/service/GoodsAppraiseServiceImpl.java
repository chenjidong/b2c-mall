package com.ppepper.goods.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.dto.SpuAppraiseDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.goods.domain.SpuAppraiseDO;
import com.ppepper.goods.mapper.SpuAppraiseMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@Service
public class GoodsAppraiseServiceImpl extends BaseServiceImpl implements GoodsAppraiseService {
    private static final Logger logger = Logger.getLogger(GoodsAppraiseServiceImpl.class.getSimpleName());
    @Autowired
    private SpuAppraiseMapper spuAppraiseMapper;

    @Override
    public AjaxResult get(Long accountId, Long appraiseId) {
        SpuAppraiseDO spuAppraiseDO = new SpuAppraiseDO();
        spuAppraiseDO.setAccountId(accountId);
        spuAppraiseDO.setId(appraiseId);

        SpuAppraiseDTO spuAppraiseDTO = copyProperties(spuAppraiseMapper.selectOne(spuAppraiseDO), SpuAppraiseDTO.class);
        return success(spuAppraiseDTO);
    }

    @Override
    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, String orderBy, Boolean isAsc, String keyword, Integer score) {

        Wrapper<SpuAppraiseDO> wrapper = new EntityWrapper<>();

        if (orderBy != null && isAsc != null)
            wrapper.orderBy(orderBy, isAsc);

        if (accountId != null)
            wrapper.eq("account_id", accountId);

        if (keyword != null)
            wrapper.like("content", keyword);

        if (score != null)
            wrapper.eq("score", score);

        List<SpuAppraiseDO> spuAppraiseDOList = spuAppraiseMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);

        AjaxResult ajaxResult = success(spuAppraiseDOList);

        return ajaxResult;
    }
}
