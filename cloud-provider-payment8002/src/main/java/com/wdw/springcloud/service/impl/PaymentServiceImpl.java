package com.wdw.springcloud.service.impl;

import com.wdw.springcloud.dao.PaymentDao;
import com.wdw.springcloud.service.PaymentService;
import com.wdw.springcloud.entites.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Create by wdw on 2020/6/12
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {

        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
