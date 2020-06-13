package com.wdw.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Create by wdw on 2020/6/13
 */
@Service
public class PaymentService {

    public String Success(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   成功:"+ id;
    }
    public String TimeOut(Integer id){
        int timenumber =3;
        try {
            TimeUnit.SECONDS.sleep(timenumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"   超时了:"+id;
    }
}
