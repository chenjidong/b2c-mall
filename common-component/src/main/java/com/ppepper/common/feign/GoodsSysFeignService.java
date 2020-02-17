package com.ppepper.common.feign;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.feign.client.GoodsFeignClient;
import com.ppepper.common.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Service
public class GoodsSysFeignService extends BaseFeignService {

    @Autowired
    private GoodsFeignClient goodsFeignClient;


    @HystrixCommand(fallbackMethod = "serviceOffline")
    public SpuDTO get(Long id) {
        return convert(goodsFeignClient.get(id), SpuDTO.class);
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
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

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public Boolean stock(Long id, Integer num) {
        AjaxResult ajaxResult = goodsFeignClient.stock(id, num);
        return ajaxResult.getCode() == AjaxResult.Type.SUCCESS.value();
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public Boolean freezeStock(Long id, Integer num) {
        AjaxResult ajaxResult = goodsFeignClient.freezeStock(id, num);
        return ajaxResult.getCode() == AjaxResult.Type.SUCCESS.value();
    }

    @HystrixCommand(fallbackMethod = "serviceOffline")
    public Boolean rollbackStock(Long skuId, Integer num) {
        AjaxResult ajaxResult = goodsFeignClient.rollbackStock(skuId, num);
        return ajaxResult.getCode() == AjaxResult.Type.SUCCESS.value();
    }


    public SpuDTO serviceOffline(Long id) {
        return null;
    }

    public List<SpuDTO> serviceOffline(Long[] ids) {
        return null;
    }

    public Boolean serviceOffline(Long skuId, Integer num) {
        return false;
    }

}
