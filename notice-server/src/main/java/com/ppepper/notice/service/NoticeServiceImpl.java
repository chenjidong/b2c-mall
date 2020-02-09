package com.ppepper.notice.service;

import com.ppepper.common.dto.NoticeDTO;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.notice.mapper.NoticeMapper;
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
    public List<NoticeDTO> selectNoticePage(Integer offset, Integer limit) {
        return noticeMapper.selectNoticePage(offset, limit);
    }

    @Override
    public Long countNotice() {
        return noticeMapper.countNotice();
    }
}
