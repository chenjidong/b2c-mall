package com.ppepper.common.feign;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Service
public class GoodsFeignService extends BaseFeignService {

    @Autowired
    private GoodsFeignClient goodsFeignClient;


    @HystrixCommand(fallbackMethod = "serviceOffline")//服务不可用时  hystrix 自动调用指定函数返回
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

        return convert(goodsFeignClient.get(id), SpuDTO.class);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")//服务不可用时  hystrix 自动调用指定函数返回
    public List<SpuDTO> getByIds(Long[] ids) {
        return convertList(goodsFeignClient.getByIds(ids), SpuDTO.class);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public List<SpuDTO> getBySkuIds(Long[] ids) {
        return convertList(goodsFeignClient.getBySkuIds(ids), SpuDTO.class);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public SpuDTO getBySkuIds(Long id) {
        List<SpuDTO> list = convertList(goodsFeignClient.getBySkuIds(new Long[]{id}), SpuDTO.class);
        return list == null ? null : list.get(0);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")//服务不可用时  hystrix 自动调用指定函数返回
    public Boolean stock(Long id, Integer num) {
        AjaxResult ajaxResult = goodsFeignClient.stock(id, num);
        return ajaxResult.getCode() == AjaxResult.Type.SUCCESS.value();
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public Boolean freezeStock(Long id, Integer num) {
        AjaxResult ajaxResult = goodsFeignClient.freezeStock(id, num);
        return ajaxResult.getCode() == AjaxResult.Type.SUCCESS.value();
    }


    public SpuDTO serviceOffline(Long id) {
        return null;
    }

    public List<SpuDTO> serviceOffline(Long[] ids) {
        return null;
    }

    public Boolean serviceOffline(Long id, Integer num) {
        return false;
    }

}
