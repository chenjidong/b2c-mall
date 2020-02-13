package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.CouponUserDTO;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-10
 */
@Service
public class CouponUserFeignService extends BaseFeignService {

    @Autowired
    private CouponFeignClient couponFeignClient;

    @HystrixCommand(fallbackMethod = "serviceOffline")//服务不可用时  hystrix 自动调用指定函数返回
    public CouponUserDTO get(Long id) {
        return convert(couponFeignClient.getByUserId(id), CouponUserDTO.class);
    }


    @HystrixCommand(fallbackMethod = "serviceOffline1")//服务不可用时  hystrix 自动调用指定函数返回
    public Boolean used(Long id) {
        AjaxResult ajaxResult = couponFeignClient.used(id);

        return ajaxResult.getCode() == AjaxResult.Type.SUCCESS.value();
    }

    public Boolean serviceOffline1(Long id) {
        return null;
    }

    public CouponUserDTO serviceOffline(Long id) {
        return null;
    }
}
