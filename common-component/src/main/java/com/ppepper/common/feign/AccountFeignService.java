package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.AccountDTO;
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
    public AccountDTO get(Long id) {
        if (StringUtils.isEmpty(id))
            return null;
        return convert(accountFeignClient.get(id), AccountDTO.class);
    }

    public AccountDTO serviceOffline(Long id) {
        return null;
    }

    public AccountDTO serviceOffline(String username) {
        return null;
    }

}
