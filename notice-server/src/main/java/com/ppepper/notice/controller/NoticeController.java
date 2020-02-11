package com.ppepper.notice.controller;

import com.ppepper.common.controller.BaseController;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;


    @RequestMapping("/user/get")
    public AjaxResult get(@RequestParam("id") Long id) {

        return noticeService.get(JwtTokenUtils.getCurrentAccountIdByToken(), id);
    }

    @RequestMapping("/user/list")
    public AjaxResult list(@RequestParam("pageNo") Integer pageNo,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam("type") Integer type,
                           @RequestParam("orderBy") String orderBy,
                           @RequestParam("isAsc") Boolean isAsc,
                           @RequestParam("title") String title) {

        return noticeService.list(JwtTokenUtils.getCurrentAccountIdByToken(), pageNo, pageSize, type, orderBy, isAsc, title);
    }
}
