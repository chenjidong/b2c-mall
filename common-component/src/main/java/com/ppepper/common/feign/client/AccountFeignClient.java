package com.ppepper.common.feign.client;

import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 * 账号服务
 */
@FeignClient(name = "account-service")
public interface AccountFeignClient {


    @RequestMapping("/sys/account/getByUsername")
    public AjaxResult getByUsername(@RequestParam("username") String username);

    @RequestMapping("/sys/account/getByPhone")
    public AjaxResult getByPhone(@RequestParam("phone") String phone);

    @RequestMapping("/sys/account/get")
    public AjaxResult get(@RequestParam("id") Long id);


    @RequestMapping("/sys/account/sendCode")
    public AjaxResult sendCode(@RequestParam("phone") String phone);

    @RequestMapping("/sys/account/create")
    public AjaxResult create(@RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("code") String code);


    @RequestMapping("/sys/account/list")
    public Page<AccountDTO> list(@RequestParam("page") Integer page,
                                 @RequestParam("limit") Integer limit,
                                 @RequestParam("nickname") String nickname,
                                 @RequestParam("orderBy") String orderBy,
                                 @RequestParam("isAsc") Boolean isAsc,
                                 @RequestParam("id") Long id,
                                 @RequestParam("status") Integer status);

    @RequestMapping("/sys/account/updateStatus")
    public AjaxResult updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status);

    @RequestMapping(value = "/sys/account/createByAdmin",method = RequestMethod.POST)
    public AjaxResult createByAdmin(@RequestParam("id") Long id,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("password") String password,
                                    @RequestParam("status") Integer status,
                                    @RequestParam("nickname") String nickname
    );


    @RequestMapping("/sys/account/delete")
    public AjaxResult delete(@RequestParam("id")Long id);


    @RequestMapping("/api/account/collect/get")
    public AjaxResult getCollect(@RequestParam("id") Long id);


    @RequestMapping("/api/account/cart/list")
    public AjaxResult getCartList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);


    @RequestMapping("/api/account/address/get")
    public AjaxResult getAddress(@RequestParam("id") Long id);

    @RequestMapping("/api/account/cart/clean")
    public AjaxResult cleanCart();

}
