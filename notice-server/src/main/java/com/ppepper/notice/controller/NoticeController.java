package com.ppepper.notice.controller;

import com.ppepper.common.controller.BaseController;
import com.ppepper.common.dto.NoticeDTO;
import com.ppepper.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;


    @RequestMapping("/selectNoticePage")
    public List<NoticeDTO> selectNoticePage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return noticeService.selectNoticePage(offset, limit);
    }

    @RequestMapping("/countNotice")
    public Long countNotice() {
        return noticeService.countNotice();
    }
}
