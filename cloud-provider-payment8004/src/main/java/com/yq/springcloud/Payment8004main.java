package com.yq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yuqian
 * @ClassName Payment8004main
 * @description:
 * @date 2023年02月24日
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Payment8004main {
    public static void main(String[] args) {
        SpringApplication.run(Payment8004main.class,args);
    }
}
