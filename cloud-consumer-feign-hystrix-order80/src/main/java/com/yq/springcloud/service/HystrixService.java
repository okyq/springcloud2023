package com.yq.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yuqian
 * @ClassName HystrixService
 * @description:
 * @date 2023年02月27日
 */
@Component
@FeignClient(value = "cloud-provider-hystrix-payment",fallback = com.yq.springcloud.service.HystrixFallbackService.class)
public interface HystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Integer id);
}
