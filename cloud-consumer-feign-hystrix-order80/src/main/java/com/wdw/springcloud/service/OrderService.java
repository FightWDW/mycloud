package com.wdw.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Create by wdw on 2020/6/13
 */

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback = OrderFallBackService.class)
public interface OrderService {

    @GetMapping("/hystrix/ok/{id}")
    public String Success(@PathVariable("id") Integer id);

    @GetMapping("/hystrix/timeout/{id}")
    public String Timeout(@PathVariable("id") Integer id);
}
