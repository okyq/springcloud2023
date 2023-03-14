package com.yq.springcloud.service;

import com.yq.springcloud.entities.CommonResult;
import com.yq.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yuqian
 * @InterfaceName PaymentFeignService
 * @description:
 * @date 2023年02月25日
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feignTimeout")
    public String paymentTimeout();
}
