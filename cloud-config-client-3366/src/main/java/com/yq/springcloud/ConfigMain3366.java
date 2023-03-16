package com.yq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yuqian
 * @ClassName ConfigMain3366
 * @description:
 * @date 2023年03月16日
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigMain3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigMain3366.class,args);
    }
}
