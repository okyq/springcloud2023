package com.yq.springcloud.service;

import com.yq.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName : PaymentService
 * @Description :
 * @Author : yuqian
 * @Date : 2023/1/30
 */
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
