package com.ppepper.goods.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ppepper.common.Const;
import com.ppepper.common.dto.SpuCategoryDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.redis.CacheComponent;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.goods.domain.SpuCategoryDO;
import com.ppepper.goods.mapper.SpuCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@Service
public class GoodsCategoryServiceImpl extends BaseServiceImpl implements GoodsCategoryService {
    private static final Logger logger = Logger.getLogger(GoodsCategoryServiceImpl.class.getSimpleName());

    @Autowired
    private SpuCategoryMapper spuCategoryMapper;

    @Autowired
    private CacheComponent cacheComponent;


    @Override
    public AjaxResult get(Long id) {
        SpuCategoryDO spuCategoryDO = spuCategoryMapper.selectById(id);
        SpuCategoryDTO spuCategoryDTO = copyProperties(spuCategoryDO, SpuCategoryDTO.class);
        return success(spuCategoryDTO);
    }

    @Override
    public AjaxResult list() {
        //若关键字为空，尝试从缓存取列表
        AjaxResult objFromCache = cacheComponent.getObj(CACHE_SPU_CATEGORY_PAGE_PREFIX+"1_999", AjaxResult.class);
        if (objFromCache != null) {
            logger.info("缓存读取");
            return objFromCache;
        }
        List<SpuCategoryDO> categoryDOList = spuCategoryMapper.selectList(new EntityWrapper<>());

        List<SpuCategoryDTO> categoryDTOList = copyListProperties(categoryDOList, SpuCategoryDTO.class);

        categoryDTOList.forEach(categoryDTO -> {
            categoryDOList.forEach(categoryDO -> {
                if (categoryDO.getParentId().equals(categoryDTO.getId())) {
                    List<SpuCategoryDTO> childrenList = categoryDTO.getChildrenList();
                    if (childrenList == null) {
                        childrenList = new LinkedList<>();
                        categoryDTO.setChildrenList(childrenList);
                    }
                    SpuCategoryDTO childSpuCategoryDTO = copyProperties(categoryDO, SpuCategoryDTO.class);

                    childSpuCategoryDTO.setChildrenList(new LinkedList<>());
                    childrenList.add(childSpuCategoryDTO);
                    categoryDOList.forEach(leaf -> {
                        if (childSpuCategoryDTO.getId().equals(leaf.getParentId())) {
                            SpuCategoryDTO leafSpuCategoryDTO = copyProperties(leaf, SpuCategoryDTO.class);
                            childSpuCategoryDTO.getChildrenList().add(leafSpuCategoryDTO);
                        }
                    });
                }
            });
        });

        AjaxResult ajaxResult = success(categoryDTOList);

        cacheComponent.putObj(CACHE_SPU_CATEGORY_PAGE_PREFIX+"1_999", ajaxResult, Const.CACHE_ONE_DAY);

        return ajaxResult;
    }
}
