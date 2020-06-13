package com.wdw.springcloud.service;

import com.wdw.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * Create by wdw on 2020/6/12
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
