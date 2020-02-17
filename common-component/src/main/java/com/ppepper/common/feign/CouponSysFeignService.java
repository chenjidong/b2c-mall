package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.feign.client.CouponFeignClient;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-10
 */
@Service
public class CouponSysFeignService extends BaseFeignService {

    @Autowired
    private CouponFeignClient couponFeignClient;

    @HystrixCommand(fallbackMethod = "rollbackUnusedOffline")
    public Boolean rollbackUnused(Long accountId, Long id) {
        return couponFeignClient.rollbackUnused(accountId, id).getCode() == AjaxResult.Type.SUCCESS.value();
    }

    @HystrixCommand(fallbackMethod = "rollbackUnusedOffline")
    public Boolean used(Long accountId, Long id) {
        return couponFeignClient.used(accountId, id).getCode() == AjaxResult.Type.SUCCESS.value();
    }

    public Boolean rollbackUnusedOffline(Long accountId, Long id) {
        return false;
    }
}
