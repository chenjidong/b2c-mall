package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.feign.client.OrderFeignClient;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-10
 */
@Service
public class OrderUserFeignService extends BaseFeignService {

    @Autowired
    private OrderFeignClient orderFeignClient;


    @HystrixCommand(fallbackMethod = "serviceOffline")
    public OrderDTO get(Long id) {
        return convert(orderFeignClient.get(id), OrderDTO.class);
    }


    public OrderDTO serviceOffline(Long id) {
        return null;
    }

}
