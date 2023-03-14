package com.yq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yuqian
 * @ClassName PaymentMain8002
 * @description:
 * @date 2023年02月22日
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8002 {
    public static void main(String[] args) {
            SpringApplication.run(PaymentMain8002.class,args);
        }
}
