package com.ppepper.common.feign;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.SpuAppraiseDTO;
import com.ppepper.common.dto.SpuCategoryDTO;
import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.model.Page;
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

    @HystrixCommand(fallbackMethod = "spuAppraiseDTOServiceOffline")
    public SpuAppraiseDTO getAppraise(Long id) {
        return goodsFeignClient.getAppraise(id);
    }

    @HystrixCommand(fallbackMethod = "selectSpuAllAppraiseServiceOffline")
    public Page<SpuAppraiseDTO> selectSpuAllAppraise(Long spuId, Integer offset, Integer size) {
        return goodsFeignClient.selectSpuAllAppraise(spuId, offset, size);
    }

    @HystrixCommand(fallbackMethod = "selectUserAllAppraiseServiceOffline")
    public Page<SpuAppraiseDTO> selectUserAllAppraise(Long userId, Integer offset, Integer size) {
        return goodsFeignClient.selectUserAllAppraise(userId, offset, size);
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

    public SpuAppraiseDTO spuAppraiseDTOServiceOffline(Long id) {
        return new SpuAppraiseDTO();
    }

    public Page<SpuAppraiseDTO> selectUserAllAppraiseServiceOffline(Long userId, Integer offset, Integer size) {
        return new Page<SpuAppraiseDTO>();
    }

    public Page<SpuAppraiseDTO> selectSpuAllAppraiseServiceOffline(Long spuId, Integer offset, Integer size) {
        return new Page<SpuAppraiseDTO>();
    }

}
