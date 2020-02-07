package com.ppepper.common;

import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@EnableFeignClients({"com.ppepper.common"}) //feign 注解调用 其他服务
public class CommonConfig {
}
