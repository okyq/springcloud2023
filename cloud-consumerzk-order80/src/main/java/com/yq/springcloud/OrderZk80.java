package com.yq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yuqian
 * @ClassName orderZk80
 * @description:
 * @date 2023年02月24日
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderZk80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZk80.class,args);
    }
}
