package com.yq.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqian
 * @ClassName PaymentController
 * @description:
 * @date 2023年03月17日
 */
@RestController
@RefreshScope
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id)
    {
        return "nacos registry, serverPort: "+ serverPort+"\t id"+id;
    }

    @GetMapping(value = "/payment/nacos/get")
    public String getConfig()
    {
        return "serverPort:  "+ serverPort+"  configInfo: "+configInfo;
    }
}
