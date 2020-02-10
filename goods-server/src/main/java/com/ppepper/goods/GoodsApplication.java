package com.ppepper.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableHystrix //启动熔断机制
@MapperScan(value = {"com.ppepper.goods.mapper"})
@EnableDiscoveryClient //eureka 观察client 端用于调用 eureka service
@SpringBootApplication(scanBasePackages = {"com.ppepper.goods", "com.ppepper.common"}, exclude = {RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class})
@EnableTransactionManagement
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

}
