package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.AddressDTO;
import com.ppepper.common.feign.client.AccountFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@Service
public class AddressFeignService extends BaseFeignService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public AddressDTO getAddress(Long id) {

        return convert(accountFeignClient.getAddress(id), AddressDTO.class);
    }

    public AddressDTO serviceOffline(Long id) {
        return null;
    }
}
