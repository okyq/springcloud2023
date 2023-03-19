package com.yq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yuqian
 * @ClassName MainConsumer83
 * @description:
 * @date 2023年03月17日
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MainConsumer83 {
    public static void main(String[] args) {
        SpringApplication.run(MainConsumer83.class,args);
    }
}
