package com.wdw.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * Create by wdw on 2020/6/13
 */
@Component
public class OrderFallBackService implements OrderService {
    @Override
    public String Success(Integer id) {
        return "OrderFallBackService:Success fall back";
    }

    @Override
    public String Timeout(Integer id) {
        return "OrderFallBackService:Timeout fall back";
    }
}
