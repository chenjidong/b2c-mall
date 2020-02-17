package com.ppepper.zuul;

import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import com.ppepper.sso.SsoApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableHystrix
@EnableZuulProxy //zuul proxy 服务网关
@EnableDiscoveryClient //eureka client
@SpringBootApplication(exclude = {MybatisPlusAutoConfiguration.class, DataSourceAutoConfiguration.class, RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class})
//排除数据库 和 redis配置
@ComponentScan(basePackages = {"com.ppepper.zuul", "com.ppepper.common.feign", "com.ppepper.common.exception","com.ppepper.sso"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {SsoApplication.class}))
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
