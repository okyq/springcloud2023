package com.yq.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author yuqian
 * @ClassName HystrixFallbackService
 * @description:
 * @date 2023年02月27日
 */
@Component
public class HystrixFallbackService implements HystrixService{
    @Override
    public String paymentInfoOK(Integer id) {
        return "通过统一的Fallback返回错误";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "通过统一的Fallback返回错误";
    }
}
