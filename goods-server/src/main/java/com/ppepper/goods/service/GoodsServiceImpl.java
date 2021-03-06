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
import java.util.Arrays;
import java.util.Date;
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

        if (categoryId != null)
            wrapper.eq("category_id", categoryId);

        wrapper.eq("status", SpuStatusType.SELLING.getCode());

        List<SpuDO> spuDOS = spuMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        List<SpuDTO> spuDTOList = copyListProperties(spuDOS, SpuDTO.class);

        spuDTOList.forEach(item -> {
            item.setDetail(null);
            List<SpuSkuDO> spuSkuDOList = spuSkuMapper.selectList(
                    new EntityWrapper<SpuSkuDO>().eq("spu_id", item.getId()));
            if (spuSkuDOList != null && !spuSkuDOList.isEmpty()) {
                List<SpuSkuDTO> spuSkuDTOList = copyListProperties(spuSkuDOList, SpuSkuDTO.class);
                item.setSkuList(spuSkuDTOList);
            }
        });

        return toAjax(spuDTOList);
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
            List<SpuSkuDTO> spuSkuDTOList = copyListProperties(spuSkuDOList, SpuSkuDTO.class);
            int sum = spuSkuDTOList.stream().mapToInt(item -> item.getStock()).sum();
            spuDTO.setSkuList(spuSkuDTOList);
            spuDTO.setStock(sum);
        }


        return success(spuDTO);
    }

    @Override
    public AjaxResult getByIds(Long[] ids) {
        List<SpuDO> spuDOS = spuMapper.selectBatchIds(Arrays.asList(ids));
        if (spuDOS != null) {
            List<SpuDTO> spuDTOList = copyListProperties(spuDOS, SpuDTO.class);
            spuDTOList.forEach(item -> {
                item.setDetail(null);
                List<SpuSkuDO> spuSkuDOList = spuSkuMapper.selectList(
                        new EntityWrapper<SpuSkuDO>()
                                .eq("spu_id", item.getId()));
                if (spuSkuDOList != null && !spuSkuDOList.isEmpty()) {
                    List<SpuSkuDTO> spuSkuDTOList = copyListProperties(spuSkuDOList, SpuSkuDTO.class);
                    item.setSkuList(spuSkuDTOList);
                }
            });
            return toAjax(spuDTOList);
        }
        return error("查询失败");
    }

    @Override
    public AjaxResult getBySkuIds(Long[] skuIds) {
        List<SpuDTO> spuDTOList = new ArrayList<>();
        for (Long skuId : skuIds) {
            SpuSkuDO spuSkuDO = spuSkuMapper.selectById(skuId);
            if (spuSkuDO != null) {
                SpuDO spuDO = spuMapper.selectById(spuSkuDO.getSpuId());
                SpuDTO spuDTO = copyProperties(spuDO, SpuDTO.class);
                spuDTO.setSkuList(new ArrayList<SpuSkuDTO>() {{
                    add(copyProperties(spuSkuDO, SpuSkuDTO.class));
                }});
                spuDTOList.add(spuDTO);
            }
        }
        return toAjax(spuDTOList);
    }

    @Override
    public AjaxResult stock(Long id, Integer num) {
        SpuSkuDO spuSkuDO = spuSkuMapper.selectById(id);
        if (spuSkuDO == null)
            return error("商品不存在 或库存为0");
        if (spuSkuDO.getFreezeStock() < num)
            return error("商品冻结库存不符");
        Integer freezeStock = spuSkuDO.getFreezeStock() - num;

        SpuSkuDO spuSkuDO1 = new SpuSkuDO();
        spuSkuDO1.setId(spuSkuDO.getId());
        spuSkuDO1.setFreezeStock(freezeStock);
        int count = spuSkuMapper.updateById(spuSkuDO1);
        return toAjax(count);
    }

    @Override
    public AjaxResult freezeStock(Long id, Integer num) {
        SpuSkuDO spuSkuDO = spuSkuMapper.selectById(id);
        if (spuSkuDO == null || spuSkuDO.getStock() <= 0)
            return error("商品不存在 或库存为0");
        if (spuSkuDO.getStock() < num)
            return error("商品库存不足");
        Integer stock = spuSkuDO.getStock() - num;
        Integer freezeStock = spuSkuDO.getFreezeStock() + num;

        SpuSkuDO spuSkuDO1 = new SpuSkuDO();
        spuSkuDO1.setId(spuSkuDO.getId());
        spuSkuDO1.setStock(stock);
        spuSkuDO1.setFreezeStock(freezeStock);
        int count = spuSkuMapper.updateById(spuSkuDO1);
        return toAjax(count);
    }

    @Override
    public AjaxResult rollbackStock(Long skuId, Integer num) {
        SpuSkuDO spuSkuDO = spuSkuMapper.selectById(skuId);
        if (spuSkuDO == null)
            return error("优惠券不存在");
        if (spuSkuDO.getFreezeStock() == null || spuSkuDO.getFreezeStock() < num)
            return error("冻结库存与回滚库存不匹配");
        spuSkuDO.setFreezeStock(spuSkuDO.getFreezeStock() - num);
        spuSkuDO.setStock(spuSkuDO.getStock() + num);
        spuSkuDO.setGmtUpdate(new Date());
        return toAjax(spuSkuMapper.updateById(spuSkuDO));
    }
}
