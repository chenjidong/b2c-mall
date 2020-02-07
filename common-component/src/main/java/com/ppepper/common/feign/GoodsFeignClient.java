package com.ppepper.common.feign;

import com.ppepper.common.dto.SpuAppraiseDTO;
import com.ppepper.common.dto.SpuCategoryDTO;
import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.model.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 * feign 调用服务
 */
@FeignClient(name = "goods-service")
public interface GoodsFeignClient {

    @RequestMapping(value = "/goods/get", method = RequestMethod.GET)
    public SpuDTO get(@RequestParam("id") Long id);

    @RequestMapping(value = "/goods/category/get", method = RequestMethod.GET)
    public SpuCategoryDTO getCategory(@RequestParam("id") Long id);

    @RequestMapping(value = "/goods/category/list", method = RequestMethod.GET)
    public List<SpuCategoryDTO> getCategoryList();

    @RequestMapping(value = "/goods/appraise/get", method = RequestMethod.GET)
    public SpuAppraiseDTO getAppraise(@RequestParam("id") Long id);

    @RequestMapping(value = "/goods/appraise/selectUserAllAppraise", method = RequestMethod.GET)
    public Page<SpuAppraiseDTO> selectUserAllAppraise(@RequestParam("userId") Long userId, @RequestParam("offset") Integer offset, @RequestParam("size") Integer size);

    @RequestMapping(value = "/goods/appraise/selectSpuAllAppraise", method = RequestMethod.GET)
    public Page<SpuAppraiseDTO> selectSpuAllAppraise(@RequestParam("spuId") Long spuId, @RequestParam("offset") Integer offset, @RequestParam("size") Integer size);

}
