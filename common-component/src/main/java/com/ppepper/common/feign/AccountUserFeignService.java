package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.CollectDTO;
import com.ppepper.common.feign.client.AccountFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@Service
public class AccountUserFeignService extends BaseFeignService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public CollectDTO getCollect(Long collectId) {

        return convert(accountFeignClient.getCollect(collectId), CollectDTO.class);
    }


    public CollectDTO serviceOffline(Long id) {
        return null;
    }


}
