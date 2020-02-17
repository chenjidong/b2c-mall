package com.ppepper.common.feign;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.feign.client.GoodsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Service
public class GoodsApiFeignService extends BaseFeignService {

    @Autowired
    private GoodsFeignClient goodsFeignClient;


    @HystrixCommand(fallbackMethod = "serviceOffline")
    public SpuDTO getGoods(Long id) {
        return convert(goodsFeignClient.getGoods(id), SpuDTO.class);
    }

    public SpuDTO serviceOffline(Long id) {
        return null;
    }

}
