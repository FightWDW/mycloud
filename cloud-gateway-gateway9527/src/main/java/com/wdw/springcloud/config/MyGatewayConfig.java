package com.wdw.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by wdw on 2020/6/13
 */
@Configuration
public class MyGatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("route_path_baidu",r -> r.path("/outweb")
//                .uri("https://tieba.baidu.com/f?kw=%BA%A3%D4%F4%CD%F5")).build();
                .uri("http://baidu.com/")).build();
        return routes.build();
    }
}
