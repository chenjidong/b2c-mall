package com.ppepper.account.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.account.domain.CollectDO;
import com.ppepper.account.mapper.CollectMapper;
import com.ppepper.common.dto.CollectDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-11
 */
@Service
public class CollectServiceImpl extends BaseServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public AjaxResult get(Long accountId, Long id) {
        CollectDO collectDO = new CollectDO();
        collectDO.setAccountId(accountId);
        collectDO.setId(id);
        collectDO = collectMapper.selectOne(collectDO);
        return toAjax(copyProperties(collectDO, CollectDTO.class));
    }

    @Override
    public AjaxResult list(Long account, Integer pageNo, Integer pageSize, Integer type) {
        Wrapper<CollectDO> wrapper = new EntityWrapper<>();
        wrapper.eq("type", type);
        List<CollectDO> collectDOS = collectMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        return toAjax(copyListProperties(collectDOS, CollectDTO.class));
    }

    @Override
    public AjaxResult save(Long accountId, Long id, Integer type, Boolean isDel) {
        CollectDO collectDO = new CollectDO();
        if (isDel) {
            collectDO.setAccountId(accountId);
            collectDO.setId(id);
            collectDO = collectMapper.selectOne(collectDO);
            if (collectDO != null) {
                int count = collectMapper.deleteById(collectDO.getId());
                return toAjax(count);
            }
        } else {
            collectDO.setAccountId(accountId);
            collectDO.setId(id);
            collectDO.setType(type);
            CollectDO oldDO = collectMapper.selectOne(collectDO);
            if (oldDO == null) {
                int count = collectMapper.insert(collectDO);
                return toAjax(count);
            }
        }

        return error("操作失败");
    }
}
