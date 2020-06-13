package com.wdw.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by wdw on 2020/6/13
 */
@Component
public class MyLb implements IbLance {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public ServiceInstance serviceInstance(List<ServiceInstance> serviceInstanceList) {
        int index = getIndex() ;
        if(serviceInstanceList == null || serviceInstanceList.size()<=0){
            return null;
        }
        return serviceInstanceList.get(index % serviceInstanceList.size());
    }

    private final Integer getIndex(){
        int current = atomicInteger.get();

        int next = current >= Integer.MAX_VALUE ? 0:current+1;
        for(;;){
            if(atomicInteger.compareAndSet(current,next)){
                System.out.println("当前是第"+current+"次访问");
                return next;
            }else{
                current = atomicInteger.get();
                next = current >= Integer.MAX_VALUE ? 0:current+1;
            }
        }
    }
}
