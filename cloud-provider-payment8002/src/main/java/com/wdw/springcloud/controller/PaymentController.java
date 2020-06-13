package com.wdw.springcloud.controller;

import com.wdw.springcloud.entites.CommonResult;
import com.wdw.springcloud.entites.Payment;
import com.wdw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by wdw on 2020/6/12
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    ///payment/create?serial=第二个订单
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("***** 插入结果："+result+"*****");
        if(result>0){
            return new CommonResult(200,"插入成功"+",server:"+serverPort,result);

        }else {
            return new CommonResult(444,"插入失败"+",server:"+serverPort,null);
        }
    }

    //http://localhost:8001/payment/get/2
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("***** 查询成功："+payment+"*****");
        if(payment != null){
            return new CommonResult(200,"查询成功"+",server:"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询失败"+",server:"+serverPort,null);
        }
    }
    @GetMapping("/getPort")
    public String getMyPort(){
        return serverPort;
    }
}
