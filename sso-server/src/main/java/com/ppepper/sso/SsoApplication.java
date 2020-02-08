package com.ppepper.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created with ChenJiDong
 * Created By 2020-02-07
 */
@MapperScan(value = {"com.ppepper.sso.mapper"})
@EnableDiscoveryClient //eureka 观察client 端用于调用 eureka service
@SpringBootApplication(scanBasePackages = {"com.ppepper.sso", "com.ppepper.common"}, exclude = {RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class})
@EnableTransactionManagement
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

}
