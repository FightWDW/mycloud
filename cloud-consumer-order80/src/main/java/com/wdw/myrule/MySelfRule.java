package com.wdw.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by wdw on 2020/6/13
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}
