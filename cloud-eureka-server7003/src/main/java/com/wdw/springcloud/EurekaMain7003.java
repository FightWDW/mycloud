package com.wdw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Create by wdw on 2020/6/13
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaMain7003 {
    public static void main(String[] args){
        SpringApplication.run(EurekaMain7003.class,args);
    }
}
