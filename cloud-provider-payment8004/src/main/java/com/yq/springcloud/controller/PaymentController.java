package com.yq.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author yuqian
 * @ClassName PaymentController
 * @description:
 * @date 2023年02月24日
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/zk")
    public String paymentzk(){
        return "springcloud with zookeeper"+port+ UUID.randomUUID().toString();
    }
}
