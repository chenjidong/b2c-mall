package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-10
 */
@Service
public class OrderFeignService extends BaseFeignService {

    @Autowired
    private OrderFeignClient orderFeignClient;


    @HystrixCommand(fallbackMethod = "serviceOffline")
    public OrderDTO get(Long id) {
        return convert(orderFeignClient.get(id), OrderDTO.class);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public OrderDTO getByOrderNo(String orderNo) {
        return convert(orderFeignClient.getByOrderNo(orderNo), OrderDTO.class);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public Boolean setStatus(String orderNo, Integer status) {
        return orderFeignClient.setStatus(orderNo, status).getCode() == AjaxResult.Type.SUCCESS.value();
    }

    public OrderDTO serviceOffline(Long id) {
        return null;
    }

    public OrderDTO serviceOffline(String orderNo) {
        return null;
    }

    public Boolean serviceOffline(String orderNo, Integer status) {
        return false;
    }
}
