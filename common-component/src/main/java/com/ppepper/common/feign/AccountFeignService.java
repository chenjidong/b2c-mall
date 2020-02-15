package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@Service
public class AccountFeignService extends BaseFeignService {

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

}
