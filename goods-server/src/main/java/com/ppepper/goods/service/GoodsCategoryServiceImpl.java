package com.ppepper.goods.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ppepper.common.dto.SpuCategoryDTO;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.goods.domain.SpuCategoryDO;
import com.ppepper.goods.mapper.SpuCategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@Service
public class GoodsCategoryServiceImpl extends BaseServiceImpl implements GoodsCategoryService {

    @Autowired
    private SpuCategoryMapper spuCategoryMapper;


    @Override
    public SpuCategoryDTO get(Long id) {

        return copyProperties(spuCategoryMapper.selectById(id), SpuCategoryDTO.class);
    }

    @Override
    public List<SpuCategoryDTO> list() {

        List<SpuCategoryDO> categoryDOList = spuCategoryMapper.selectList(new EntityWrapper<>());
        
        List<SpuCategoryDTO> categoryDTOList = new LinkedList<>();
        categoryDOList.forEach(categoryDO -> {
            if (categoryDO.getParentId() == 0) {
                categoryDTOList.add(copyProperties(categoryDO, SpuCategoryDTO.class));
            }
        });


        categoryDTOList.forEach(categoryDTO -> {
            categoryDOList.forEach(categoryDO -> {
                if (categoryDO.getParentId().equals(categoryDTO.getId())) {
                    List<SpuCategoryDTO> childrenList = categoryDTO.getChildrenList();
                    if (childrenList == null) {
                        childrenList = new LinkedList<>();
                        categoryDTO.setChildrenList(childrenList);
                    }
                    SpuCategoryDTO childSpuCategoryDTO = copyProperties(categoryDO,SpuCategoryDTO.class);

                    childSpuCategoryDTO.setChildrenList(new LinkedList<>());
                    childrenList.add(childSpuCategoryDTO);
                    categoryDOList.forEach(leaf -> {
                        if (childSpuCategoryDTO.getId().equals(leaf.getParentId())) {
                            SpuCategoryDTO leafSpuCategoryDTO = copyProperties(leaf,SpuCategoryDTO.class);
                            childSpuCategoryDTO.getChildrenList().add(leafSpuCategoryDTO);
                        }
                    });
                }
            });
        });
        return categoryDTOList;
    }
}
