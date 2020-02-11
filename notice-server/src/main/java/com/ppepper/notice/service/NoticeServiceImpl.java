package com.ppepper.notice.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.common.dto.NoticeDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.notice.domain.NoticeDO;
import com.ppepper.notice.mapper.NoticeMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public AjaxResult get(Long accountId, Long id) {
        NoticeDO couponUserDO = new NoticeDO();
        couponUserDO.setAccountId(accountId);
        couponUserDO.setId(id);
        return success("", copyProperties(noticeMapper.selectOne(couponUserDO), NoticeDTO.class));
    }

    @Override
    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, Integer type, String orderBy, Boolean isAsc, String title) {
        Wrapper<NoticeDO> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(title))
            wrapper.like("title", title);
        if (orderBy != null && isAsc != null)
            wrapper.orderBy(orderBy, isAsc);
        if (type != null)
            wrapper.eq("type", type);

        wrapper.eq("account_id", accountId);
        List<NoticeDO> couponDOList = noticeMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        if (couponDOList != null && !couponDOList.isEmpty()) {
            return success(copyListProperties(couponDOList, NoticeDTO.class));
        }
        return error();
    }
}
