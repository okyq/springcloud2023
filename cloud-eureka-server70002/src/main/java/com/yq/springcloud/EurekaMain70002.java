package com.yq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yuqian
 * @ClassName EurekaMain70002
 * @description:
 * @date 2023年02月25日
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain70002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain70002.class, args);
    }
}
