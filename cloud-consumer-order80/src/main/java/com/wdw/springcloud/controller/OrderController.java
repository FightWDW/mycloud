package com.wdw.springcloud.controller;

import com.wdw.springcloud.entites.CommonResult;
import com.wdw.springcloud.entites.Payment;
import com.wdw.springcloud.lb.MyLb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.Set;

/**
 * Create by wdw on 2020/6/12
 */
@RestController
@RequestMapping(value = "/consumer/order")
@Slf4j
public class OrderController {

    //单机版
//    private static final String PAYMENT_URL = "http://localhost:8001";
    //集群版
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MyLb myLb;

    @GetMapping("/create")
    public CommonResult<Payment> create(Payment payment){
        /*
        注意，post的这个方法，传递的是JSON数据，也就是说在提供者的对应方法中，需要加上@requestbody这个注解才能获取
        到数据
         */
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/createEntity")
    public CommonResult<Payment> create2(Payment payment){
        ResponseEntity<CommonResult> commonResultResponseEntity =
                restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if(commonResultResponseEntity.getStatusCode().is2xxSuccessful()){
            log.info(String.valueOf(commonResultResponseEntity.getHeaders()));
        }
        return commonResultResponseEntity.getBody();
    }

    @GetMapping("/getEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            log.info(String.valueOf(forEntity.getHeaders()));
        }
        return forEntity.getBody();
    }

    @GetMapping("/port")
    public String getServicePort(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance serviceInstance = myLb.serviceInstance(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/getPort",String.class);
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element:services){
            log.info(element);
            List<ServiceInstance> instances = discoveryClient.getInstances(element);
            for(ServiceInstance instance : instances){
                log.info(instance.getHost());
                log.info(instance.getInstanceId());
                log.info(String.valueOf(instance.getHost()));
                log.info(String.valueOf(instance.getUri()));
                log.info("------------");
            }
        }
        return discoveryClient;
    }
}
