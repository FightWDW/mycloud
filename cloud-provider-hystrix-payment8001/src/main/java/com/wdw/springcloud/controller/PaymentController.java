package com.wdw.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wdw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Create by wdw on 2020/6/13
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/hystrix/ok/{id}")
    public String Success(@PathVariable("id") Integer id){
        return  paymentService.Success(id);
    }

    @GetMapping("/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String Timeout(@PathVariable("id") Integer id){
//        int age = 10/0;
        return  paymentService.TimeOut(id);
    }
    public String TimeoutHandler(Integer id){
        return "线程池："+ Thread.currentThread().getName()+"   系统繁忙，请稍后再试："+"o(╥﹏╥)o";
    }
}
