package com.ppepper.common.feign;

import com.alibaba.fastjson.JSON;
import com.ppepper.common.model.AjaxResult;

/**
 * Created with ChenJiDong
 * Created By 2020-02-10
 */
public class BaseFeignService {


    public <T> T convert(AjaxResult ajaxResult, Class<T> clazz) {
        try {
            if (ajaxResult.getCode() == AjaxResult.Type.SUCCESS.value()) {
                String json = JSON.toJSONString(ajaxResult.get(AjaxResult.DATA_TAG));
                return JSON.parseObject(json, clazz);
            }
        } catch (Exception e) {
            // TODO: 2020-02-10 转换失败
        }
        return null;
    }
}
