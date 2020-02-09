package com.ppepper.common.feign;

import com.ppepper.common.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@FeignClient(name = "account-service")
public interface AccountFeignClient {

    @RequestMapping("/api/account/getByUsername")
    public AccountDTO getByUsername(@RequestParam("phone") String phone);

}
