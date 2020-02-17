package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.CouponUserDTO;
import com.ppepper.common.feign.client.CouponFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-10
 */
@Service
public class CouponApiFeignService extends BaseFeignService {

    @Autowired
    private CouponFeignClient couponFeignClient;

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public CouponUserDTO getUserCoupon(Long id) {
        return convert(couponFeignClient.getUserCoupon(id), CouponUserDTO.class);
    }

    public CouponUserDTO serviceOffline(Long id) {
        return null;
    }
}
