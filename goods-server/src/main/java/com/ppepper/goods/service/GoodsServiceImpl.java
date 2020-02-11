package com.ppepper.goods.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.dto.SpuSkuDTO;
import com.ppepper.common.enums.SpuStatusType;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.goods.domain.SpuDO;
import com.ppepper.goods.domain.SpuSkuDO;
import com.ppepper.goods.mapper.SpuMapper;
import com.ppepper.goods.mapper.SpuSkuMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {
    private static final Logger logger = Logger.getLogger(GoodsServiceImpl.class.getSimpleName());
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuSkuMapper spuSkuMapper;

    @Override
    public AjaxResult list(Integer pageNo, Integer pageSize, Long categoryId, String orderBy, Boolean isAsc, String title) {


        Wrapper<SpuDO> wrapper = new EntityWrapper<>();
        if (title != null)
            wrapper.like("title", title);

        if (orderBy != null && isAsc != null) {
            wrapper.orderBy(orderBy, isAsc);
        }

        wrapper.eq("status", SpuStatusType.SELLING.getCode());

        List<SpuDO> spuDOS = spuMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        List<SpuDTO> spuDTOList = copyListProperties(spuDOS, SpuDTO.class);

        return success(spuDTOList);
    }

    @Override
    public AjaxResult get(Long spuId) {

        SpuDO spuDO = spuMapper.selectById(spuId);
        if (spuDO == null)
            return error("商品不存在");

        SpuDTO spuDTO = copyProperties(spuDO, SpuDTO.class);

        List<SpuSkuDO> spuSkuDOList = spuSkuMapper.selectList(
                new EntityWrapper<SpuSkuDO>()
                        .eq("spu_id", spuId));

        if (spuSkuDOList != null && !spuSkuDOList.isEmpty()) {
            List<SpuSkuDTO> spuSkuDTOList = new ArrayList<>();
            for (SpuSkuDO spuSkuDO : spuSkuDOList) {
                SpuSkuDTO spuSkuDTO = copyProperties(spuSkuDO, SpuSkuDTO.class);
                spuSkuDTOList.add(spuSkuDTO);
            }
            spuDTO.setSkuList(spuSkuDTOList);

            int sum = spuSkuDOList.stream().mapToInt(item -> item.getStock()).sum();
            spuDTO.setStock(sum);
        }


        return success(spuDTO);
    }
}
