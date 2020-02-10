package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.CouponDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-10
 */
@Service
public class CouponFeignService extends BaseFeignService {

    @Autowired
    private CouponFeignClient couponFeignClient;

    @HystrixCommand(fallbackMethod = "serviceOffline")//服务不可用时  hystrix 自动调用指定函数返回
    public CouponDTO get(Long id) {
        return convert(couponFeignClient.get(id), CouponDTO.class);
    }

    public CouponDTO serviceOffline(Long id) {
        return null;
    }
}
