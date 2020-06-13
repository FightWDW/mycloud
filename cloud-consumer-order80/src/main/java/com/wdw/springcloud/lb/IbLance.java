package com.wdw.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * Create by wdw on 2020/6/13
 */
public interface IbLance {
    ServiceInstance serviceInstance(List<ServiceInstance> serviceInstanceList);
}
