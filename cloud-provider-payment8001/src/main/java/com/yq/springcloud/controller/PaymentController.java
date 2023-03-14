package com.yq.springcloud.controller;

import com.yq.springcloud.entities.CommonResult;
import com.yq.springcloud.entities.Payment;
import com.yq.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : PaymentController
 * @Description :
 * @Author : yuqian
 * @Date : 2023/1/30
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverport;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if(result>0) {
            return new CommonResult(200,"success insert, fromPort: "+serverport, result);
        } else {
            return new CommonResult(400,"insert fail", null);
        }
    }
    @GetMapping (value = "/payment/get/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if(payment!= null) {
            return new CommonResult(200,"success fromPort: "+serverport, payment);
        } else {
            return new CommonResult(400,"没有查询到"+id, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        log.info("*******service*****"+services);
        for (String element : services) {
            //System.out.println(element);
            log.info("*********element:" +element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        log.info("****instence*****"+instances);
        for (ServiceInstance element : instances) {
            log.info(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/feignTimeout")
    public String paymentTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverport;
    }

}
