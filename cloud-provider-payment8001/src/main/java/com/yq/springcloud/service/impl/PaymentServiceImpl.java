package com.yq.springcloud.service.impl;

import com.yq.springcloud.dao.PaymentDao;
import com.yq.springcloud.entities.CommonResult;
import com.yq.springcloud.entities.Payment;
import com.yq.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName : PaymentServiceImpl
 * @Description :
 * @Author : yuqian
 * @Date : 2023/1/30
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;
    public int create(Payment payment){
        return  paymentDao.create(payment);
    }
    public Payment getPaymentById(@Param("id") Long id) {
        return paymentDao.getPaymentById(id);
    }

}
