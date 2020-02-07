package com.ppepper.common.feign;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.SpuCategoryDTO;
import com.ppepper.common.dto.SpuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Service
public class GoodsFeignService {

    @Autowired
    private GoodsFeignClient goodsFeignClient;


    @HystrixCommand(fallbackMethod = "spuDTOServiceOffline")//服务不可用时  hystrix 自动调用指定函数返回
    public SpuDTO get(Long id) {
        //eureka 调度
//        String service = "goods-service";
//        String url = "http://" + service + "/goods/" + id;

        //获取第一个服务
//        List<ServiceInstance> instances = discoveryClient.getInstances(service);
//        if (instances.isEmpty()){
//            return null;
//        }
//        String url = "http://" + instances.get(0).getHost()+":"+instances.get(0).getPort() + "/goods/" + id;


//        return restTemplate.getForObject(url, GoodsDTO.class);
        SpuDTO spuDTO = goodsFeignClient.get(id);
        return spuDTO;
    }


    @HystrixCommand(fallbackMethod = "spuCategoryDTOServiceOffline")
    public SpuCategoryDTO getCategory(Long id) {
        return goodsFeignClient.getCategory(id);
    }

    @HystrixCommand(fallbackMethod = "spuCategorysDTOServiceOffline")
    public List<SpuCategoryDTO> getCategoryList() {
        return goodsFeignClient.getCategoryList();
    }

    public SpuDTO spuDTOServiceOffline(Long id) {
        return new SpuDTO();
    }

    public SpuCategoryDTO spuCategoryDTOServiceOffline(Long id) {
        return new SpuCategoryDTO();
    }

    public List<SpuCategoryDTO> spuCategorysDTOServiceOffline() {
        return new ArrayList<>();
    }


}
