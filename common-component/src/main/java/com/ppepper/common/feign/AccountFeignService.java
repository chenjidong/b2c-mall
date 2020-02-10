package com.ppepper.common.feign;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@Service
public class AccountFeignService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    @HystrixCommand(fallbackMethod = "getByUsernameServiceOffline")
    public AccountDTO getByUsername(String username) {
        AjaxResult ajaxResult = accountFeignClient.getByUsername(username);
        try {
            if (ajaxResult.getCode() == AjaxResult.Type.SUCCESS.value()) {
                String json = JSON.toJSONString(ajaxResult.get(AjaxResult.DATA_TAG));
                AccountDTO accountDTO = JSON.parseObject(json, AccountDTO.class);
                return accountDTO;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public AccountDTO serviceOffline(String username) {
        return null;
    }
}
