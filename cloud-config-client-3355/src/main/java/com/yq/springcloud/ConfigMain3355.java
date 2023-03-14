package com.yq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yuqian
 * @ClassName ConfigMain3355
 * @description:
 * @date 2023年03月05日
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigMain3355 {
    public static void main(String[] args)
    {
        SpringApplication.run(ConfigMain3355.class,args);
    }
}
