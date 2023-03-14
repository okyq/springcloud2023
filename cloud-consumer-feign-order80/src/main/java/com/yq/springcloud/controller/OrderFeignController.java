package com.yq.springcloud.controller;

import com.yq.springcloud.entities.CommonResult;
import com.yq.springcloud.entities.Payment;
import com.yq.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuqian
 * @ClassName OrderFeignController
 * @description:
 * @date 2023年02月25日
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id){
        return paymentFeignService.getById(id);
    }

    @GetMapping(value = "/consumer/payment/feignTimeout")
    public String paymentTimeout() {
        return paymentFeignService.paymentTimeout();
    }
}
