package com.ppepper.goods.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.Const;
import com.ppepper.common.dto.SpuAppraiseDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.redis.CacheComponent;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.goods.domain.SpuAppraiseDO;
import com.ppepper.goods.mapper.SpuAppraiseMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private CacheComponent cacheComponent;


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

        if (StringUtils.isEmpty(keyword) && score == null) {
            //若关键字为空，尝试从缓存取列表
            AjaxResult objFromCache = cacheComponent.getObj(CACHE_SPU_APPRAISE_PAGE_PREFIX + pageNo + "_" + pageSize + "_" + orderBy + "_" + isAsc, AjaxResult.class);
            if (objFromCache != null) {
                logger.info("缓存读取");
                return objFromCache;
            }
        }
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
        if (StringUtils.isEmpty(keyword) && score == null) {
            cacheComponent.putObj(CACHE_SPU_APPRAISE_PAGE_PREFIX + pageNo + "_" + pageSize + "_" + orderBy + "_" + isAsc, ajaxResult, Const.CACHE_ONE_DAY);
        }

        return ajaxResult;
    }
}
