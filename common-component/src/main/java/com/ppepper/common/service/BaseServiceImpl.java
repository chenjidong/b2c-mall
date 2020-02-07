package com.ppepper.common.service;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-06
 */
public class BaseServiceImpl {


    protected <T> T copyProperties(Object source, Class clazz) {
        try {
            T target = (T) clazz.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected <T> List<T> copyProperties(List<Object> sources, Class clazz) {
        List<T> targets = new ArrayList<>();
        for (Object obj : sources) {
            targets.add(copyProperties(obj, clazz));
        }
        return targets;
    }

}
