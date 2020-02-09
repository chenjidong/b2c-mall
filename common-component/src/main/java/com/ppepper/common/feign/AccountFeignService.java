package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.AccountDTO;
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

        return accountFeignClient.getByUsername(username);
    }

    public AccountDTO getByUsernameServiceOffline(String username) {
        return new AccountDTO();
    }
}
