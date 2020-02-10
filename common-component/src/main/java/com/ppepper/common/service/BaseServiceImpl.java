package com.ppepper.common.service;

import com.ppepper.common.model.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-06
 */
public class BaseServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);


    protected <T> T copyProperties(Object source, Class clazz) {
        if (source == null)
            return null;
        T target = null;
        try {
            target = (T) clazz.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            logger.info(clazz.getSimpleName() + "：转换失败");
        }
        return target;
    }

    protected <T> List<T> copyListProperties(List<?> sources, Class clazz) {
        List<T> targets = new ArrayList<>();
        for (Object obj : sources) {
            T target = copyProperties(obj, clazz);
            if (target != null)
                targets.add(target);
        }
        return targets;
    }


    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * 返回成功消息 且携带数据
     */
    public AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    public AjaxResult success(String msg, Object data) {
        return AjaxResult.success(msg, data);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public AjaxResult error(AjaxResult.Type type, String message) {
        return new AjaxResult(type, message);
    }

}
