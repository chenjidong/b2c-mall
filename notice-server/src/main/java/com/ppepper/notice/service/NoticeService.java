package com.ppepper.notice.service;

import com.ppepper.common.dto.NoticeDTO;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
public interface NoticeService {

    public List<NoticeDTO> selectNoticePage(Integer offset, Integer limit);

    public Long countNotice();
}
