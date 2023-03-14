package com.yq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yuqian
 * @ClassName ConsumerMain80
 * @description:
 * @date 2023年02月27日
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class ConsumerMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain80.class, args);
    }
}
