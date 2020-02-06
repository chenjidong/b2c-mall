package com.ppepper.goods.service;


import com.baomidou.mybatisplus.entity.Column;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.Const;
import com.ppepper.common.component.CacheComponent;
import com.ppepper.common.dto.SkuDTO;
import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.enums.SpuStatusType;
import com.ppepper.common.model.Page;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.goods.domain.SkuDO;
import com.ppepper.goods.domain.SpuDO;
import com.ppepper.goods.mapper.SkuMapper;
import com.ppepper.goods.mapper.SpuMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private CacheComponent cacheComponent;

    @Autowired
    private SkuMapper skuMapper;

    private static final Column[] baseColumns = {
            Column.create().column("id"),
            Column.create().column("original_price").as("originalPrice"),
            Column.create().column("price"),
            Column.create().column("vip_price").as("vipPrice"),
            Column.create().column("title"),
            Column.create().column("sales"),
            Column.create().column("img"),
            Column.create().column("description"),
            Column.create().column("category_id").as("categoryId"),
            Column.create().column("freight_template_id").as("freightTemplateId"),
            Column.create().column("unit"),
            Column.create().column("status")};

    /**
     * SPU 销量缓存
     */
    private static final String CA_SPU_SALES_HASH = "CA_SPU_SALES_HASH";

    @Override
    public Page<SpuDTO> getGoodsPage(Integer pageNo, Integer pageSize, Long categoryId, String orderBy, Boolean isAsc, String title) {
        Wrapper<SpuDO> wrapper = new EntityWrapper<SpuDO>();


        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        } else {
            //若关键字为空，尝试从缓存取列表
            Page objFromCache = cacheComponent.getObj(CA_SPU_PAGE_PREFIX + categoryId + "_" + pageNo + "_" + pageSize + "_" + orderBy + "_" + isAsc, Page.class);
            if (objFromCache != null) {
                return objFromCache;
            }
        }

        if (orderBy != null && isAsc != null) {
            wrapper.orderBy(orderBy, isAsc);
        }

        wrapper.eq("status", SpuStatusType.SELLING.getCode());
        wrapper.setSqlSelect(baseColumns);
        List<SpuDO> spuDOS = spuMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        //组装SPU
        List<SpuDTO> spuDTOList = new ArrayList<>();
        spuDOS.forEach(item -> {
            SpuDTO spuDTO = new SpuDTO();
            BeanUtils.copyProperties(item, spuDTO);
            Map<String, String> hashAll = cacheComponent.getHashAll(CA_SPU_SALES_HASH);
            if (hashAll != null) {
                String salesStr = hashAll.get("S" + item.getId());
                if (!StringUtils.isEmpty(salesStr)) {
                    spuDTO.setSales(new Integer(salesStr));
                }
            }
            spuDTOList.add(spuDTO);
        });

        Integer count = spuMapper.selectCount(wrapper);
        Page<SpuDTO> page = new Page<>(spuDTOList, pageNo, pageSize, count);
        if (StringUtils.isEmpty(title)) {
            //若关键字为空，制作缓存
            cacheComponent.putObj(CA_SPU_PAGE_PREFIX + categoryId + "_" + pageNo + "_" + pageSize + "_" + orderBy + "_" + isAsc, page, Const.CACHE_ONE_DAY);
        }
        return page;
    }

    @Override
    public SpuDTO getGoods(Long spuId) {

        SpuDO spuDO = spuMapper.selectById(spuId);
        SpuDTO spuDTO = new SpuDTO();
        BeanUtils.copyProperties(spuDO, spuDTO);

        List<SkuDO> skuDOList = skuMapper.selectList(
                new EntityWrapper<SkuDO>()
                        .eq("spu_id", spuId));

        List<SkuDTO> skuDTOList = new ArrayList<>();
        if (skuDOList != null && !skuDOList.isEmpty()) {
            for (SkuDO skuDO : skuDOList) {
                SkuDTO skuDTO = new SkuDTO();
                BeanUtils.copyProperties(skuDO, skuDTO);
                skuDTOList.add(skuDTO);
            }
            spuDTO.setSkuList(skuDTOList);
        }

        String salesStr = cacheComponent.getHashRaw(CA_SPU_SALES_HASH, "S" + spuId);
        if (!StringUtils.isEmpty(salesStr)) {
            spuDTO.setSales(new Integer(salesStr));
        }
        int sum = skuDOList.stream().mapToInt(item -> item.getStock()).sum();
        spuDTO.setStock(sum);

        return spuDTO;
    }
}
