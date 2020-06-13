package com.wdw.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wdw.springcloud.service.OrderService;
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
//@DefaultProperties(defaultFallback = "defaultFallBackMethod")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String Success(@PathVariable("id") Integer id){
        log.info("成功: "+id);
        return  orderService.Success(id);
    }
    @GetMapping("/consumer/hystrix/timeout/{id}")
    @HystrixCommand
    public String Timeout(@PathVariable("id") Integer id){
        int age = 10/0;
        log.info("超时: "+id);
        return  orderService.Timeout(id);
    }

    public String defaultFallBackMethod(){
        return Thread.currentThread().getName()+"当前系统繁忙或者出错，请稍后再尝试";
    }


    //========+服务熔断
    @GetMapping("/order/circuit/{id}")
    //具体的属性去HystrixCommandProperties这个类中去查找
    @HystrixCommand(fallbackMethod = "breakTest_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//表示10次请求内
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//在10S中以内
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少跳闸
    })
    public String breakTest(@PathVariable("id")Long id){
        if(id < 0){
            throw new RuntimeException();
        }
        String serial = IdUtil.simpleUUID();
        return "调用成功："+serial;

    }

    public String breakTest_fallback(Long id){
        return "不能为负数，请稍后再试";
    }
}
