package com.ppepper.sso;

import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import com.ppepper.common.mybatis.MybatisPlusConfig;
import com.ppepper.common.redis.RedisAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@EnableHystrix
@EnableDiscoveryClient //eureka client
@SpringBootApplication(exclude = {MybatisPlusAutoConfiguration.class, DataSourceAutoConfiguration.class, RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class})
//排除数据库 和 redis配置
@ComponentScan(basePackages = {"com.ppepper.sso", "com.ppepper.common.feign", "com.ppepper.common.exception"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {MybatisPlusConfig.class, RedisAutoConfig.class}))
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

}
