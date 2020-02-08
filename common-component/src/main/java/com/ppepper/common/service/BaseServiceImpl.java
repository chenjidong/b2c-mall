package com.ppepper.common.service;

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
        try {
            T target = (T) clazz.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            logger.info(clazz.getSimpleName() + "：转换失败");
        }
        return null;
    }

    protected <T> List<T> copyListProperties(List<?> sources, Class clazz) {
        List<T> targets = new ArrayList<>();
        for (Object obj : sources) {
            targets.add(copyProperties(obj, clazz));
        }
        return targets;
    }

}
