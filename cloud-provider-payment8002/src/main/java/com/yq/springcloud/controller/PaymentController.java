package com.yq.springcloud.controller;

import com.yq.springcloud.entities.CommonResult;
import com.yq.springcloud.entities.Payment;
import com.yq.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if(payment!= null) {
            return new CommonResult(200,"success fromPort: "+serverport, payment);
        } else {
            return new CommonResult(400,"没有查询到"+id, null);
        }
    }

}
