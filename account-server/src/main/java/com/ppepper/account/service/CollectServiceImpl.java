package com.ppepper.account.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.account.domain.AccountDO;
import com.ppepper.account.domain.CollectDO;
import com.ppepper.account.mapper.AccountMapper;
import com.ppepper.account.mapper.CollectMapper;
import com.ppepper.common.dto.CollectDTO;
import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.enums.CollectType;
import com.ppepper.common.feign.GoodsSysFeignService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-11
 */
@Service
public class CollectServiceImpl extends BaseServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private GoodsSysFeignService goodsSysFeignService;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AjaxResult get(Long accountId, Long id) {
        CollectDO collectDO = new CollectDO();
        collectDO.setAccountId(accountId);
        collectDO.setId(id);
        collectDO = collectMapper.selectOne(collectDO);
        if (collectDO == null)
            return error();
        CollectDTO collectDTO = copyProperties(collectDO, CollectDTO.class);
        if (collectDO.getType() == CollectType.SPU.getCode()) {
            SpuDTO spuDTO = goodsSysFeignService.get(collectDTO.getCollectId());
            collectDTO.setSpuDTO(spuDTO);
        }
        return toAjax(collectDTO);
    }

    @Override
    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, Integer type) {
        Wrapper<CollectDO> wrapper = new EntityWrapper<>();
        wrapper.eq("type", type);
        wrapper.eq("account_id", accountId);
        List<CollectDO> collectDOS = collectMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        if (collectDOS != null) {
            List<CollectDTO> collectDTOList = copyListProperties(collectDOS, CollectDTO.class);
            List<Long> longList = new ArrayList<>();
            collectDTOList.forEach(item -> {
                longList.add(item.getCollectId());
            });
            List<SpuDTO> spuDTOList = goodsSysFeignService.getByIds(longList.toArray(new Long[]{}));
            if (spuDTOList != null) {
                collectDTOList.forEach(item -> spuDTOList.forEach(item1 -> {
                    if (item1.getId().equals(item.getCollectId())) {
                        item.setSpuDTO(item1);
                    }
                }));
            }
            return toAjax(collectDTOList);
        }

        return error("获取失败");
    }

    @Override
    public AjaxResult save(Long accountId, Long id, Integer type, Boolean isDel) {
        AccountDO accountDO = accountMapper.selectById(accountId);
        if (accountDO == null)
            error("用户不存在");
        if (type == CollectType.SPU.getCode()) {
            SpuDTO spuDTO = goodsSysFeignService.get(id);
            if (spuDTO == null)
                return error("商品不存在");
        } else if (type == CollectType.SHOP.getCode()) {
            // TODO: 2020-02-11 查询店铺
        } else
            return error("未知的收藏类型");
        CollectDO collectDO = new CollectDO();
        if (isDel) {
            collectDO.setAccountId(accountId);
            collectDO.setCollectId(id);
            collectDO = collectMapper.selectOne(collectDO);
            if (collectDO != null) {
                int count = collectMapper.deleteById(collectDO.getId());
                return toAjax(count);
            }
        } else {
            collectDO.setAccountId(accountId);
            collectDO.setCollectId(id);
            collectDO.setType(type);
            CollectDO oldDO = collectMapper.selectOne(collectDO);
            if (oldDO == null) {
                collectDO.setGmtCreate(new Date());
                collectDO.setGmtUpdate(new Date());
                int count = collectMapper.insert(collectDO);
                return toAjax(count);
            } else
                return success("已收藏！");
        }

        return error("操作失败");
    }
}
