package com.ppepper.common.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.CartDTO;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-09
 */
@Service
public class CartFeignService extends BaseFeignService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public List<CartDTO> getCartList(Integer pageNo, Integer pageSize) {

        return convertList(accountFeignClient.getCartList(pageNo, pageSize), CartDTO.class);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public Boolean cleanCart() {
        AjaxResult ajaxResult = accountFeignClient.cleanCart();
        return ajaxResult.getCode() == AjaxResult.Type.SUCCESS.value();
    }

    public List<CartDTO> serviceOffline(Integer pageNo, Integer pageSize) {
        return null;
    }

    public Boolean serviceOffline() {
        return false;
    }
}
