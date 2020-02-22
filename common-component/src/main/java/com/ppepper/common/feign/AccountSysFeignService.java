package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.feign.client.AccountFeignClient;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@Service
public class AccountSysFeignService extends BaseFeignService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public AccountDTO getByUsername(String username) {
        if (StringUtils.isEmpty(username))
            return null;
        return convert(accountFeignClient.getByUsername(username), AccountDTO.class);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public AccountDTO getByPhone(String phone) {
        if (StringUtils.isEmpty(phone))
            return null;
        return convert(accountFeignClient.getByPhone(phone), AccountDTO.class);
    }


    @HystrixCommand(fallbackMethod = "serviceOffline")
    public AccountDTO get(Long id) {
        if (StringUtils.isEmpty(id))
            return null;
        return convert(accountFeignClient.get(id), AccountDTO.class);
    }

    @HystrixCommand(fallbackMethod = "sendCodeServiceOffline")
    public AjaxResult sendCode(String phone) {
        return accountFeignClient.sendCode(phone);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public AjaxResult create(String phone, String password, String code) {
        return accountFeignClient.create(phone, password, code);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public Page<AccountDTO> list(Integer page,
                                 Integer limit,
                                 String nickname,
                                 String orderBy,
                                 Boolean isAsc,
                                 Long id,
                                 Integer status) {
        return accountFeignClient.list(page, limit, nickname, orderBy, isAsc, id, status);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public AjaxResult updateStatus(Long id, Integer status) {
        if (StringUtils.isEmpty(id))
            return null;
        AjaxResult ajaxResult = accountFeignClient.updateStatus(id, status);
        return ajaxResult;
    }

    @HystrixCommand(fallbackMethod = "serviceOffline1")
    public AjaxResult createByAdmin(AccountDTO accountDTO) {
        AjaxResult ajaxResult = accountFeignClient.createByAdmin(accountDTO.getId() == null ? 0L : accountDTO.getId(), accountDTO.getPhone(), accountDTO.getPassword(), accountDTO.getStatus(), accountDTO.getNickname());
        return ajaxResult;
    }

    @HystrixCommand(fallbackMethod = "serviceOffline1")
    public AjaxResult delete(Long id) {
        AjaxResult ajaxResult = accountFeignClient.delete(id);
        return ajaxResult;
    }

    public AccountDTO serviceOffline(Long id) {
        return null;
    }

    public AccountDTO serviceOffline(String username) {
        return null;
    }

    public AjaxResult sendCodeServiceOffline(String phone) {
        return AjaxResult.error("发送异常");
    }

    public AjaxResult serviceOffline(String phone, String password, String code) {
        return AjaxResult.error("注册异常");
    }


    public Page<AccountDTO> serviceOffline(Integer page,
                                           Integer limit,
                                           String nickname,
                                           String orderBy,
                                           Boolean isAsc,
                                           Long id,
                                           Integer status) {
        return new Page<>();
    }

    public AjaxResult serviceOffline(Long id, Integer status) {
        return AjaxResult.error("获取异常");
    }

    public AjaxResult serviceOffline1(AccountDTO accountDTO) {
        return AjaxResult.error("获取异常");
    }

    public AjaxResult serviceOffline1(Long id) {
        return AjaxResult.error("获取异常");
    }


}
