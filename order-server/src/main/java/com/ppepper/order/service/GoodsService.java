package com.ppepper.order.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.order.model.GoodsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Service
public class GoodsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;//手动指定调用 服务

    @Autowired
    private GoodsFeignClient goodsFeignClient;


    @HystrixCommand(fallbackMethod = "findGoodsByIdServiceOffline")//服务不可用时  hystrix 自动调用指定函数返回
    public GoodsDTO findGoodsById(Long id) {
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

        return goodsFeignClient.findGoodsById(id);
    }

    public GoodsDTO findGoodsByIdServiceOffline(Long id) {
        return new GoodsDTO(id, "查询商品信息出错", "", "", 0.0F);
    }
}
