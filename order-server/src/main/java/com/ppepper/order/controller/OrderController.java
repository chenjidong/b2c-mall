package com.ppepper.order.controller;


import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.feign.AccountFeignService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.utils.JwtTokenUtils;
import com.ppepper.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountFeignService accountFeignService;

    @RequestMapping(value = "/get")
    public AjaxResult get(@RequestParam("id") Long id) {
        AccountDTO accountDTO = accountFeignService.getByUsername(JwtTokenUtils.getRealUsername());
        Long accountId = accountDTO == null ? 0L : accountDTO.getId();

        return orderService.get(id, accountId);
    }

    @RequestMapping(value = "/list")
    public AjaxResult list(@RequestParam("pageNo") Integer pageNo,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam("status") Integer status,
                           @RequestParam("orderBy") String orderBy,
                           @RequestParam("isAsc") Boolean isAsc) {
        AccountDTO accountDTO = accountFeignService.getByUsername(JwtTokenUtils.getRealUsername());
        Long accountId = accountDTO == null ? 0L : accountDTO.getId();

        return orderService.list(pageNo, pageSize, status, accountId, orderBy, isAsc);
    }
}
