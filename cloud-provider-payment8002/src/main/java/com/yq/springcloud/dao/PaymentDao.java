package com.yq.springcloud.dao;

import com.yq.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName : PaymentDao
 * @Description :
 * @Author : yuqian
 * @Date : 2023/1/30
 */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
