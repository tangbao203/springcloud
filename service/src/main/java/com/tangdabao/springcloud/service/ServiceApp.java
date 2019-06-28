package com.tangdabao.springcloud.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.tangdabao.springcloud.service.dao")
public class ServiceApp {
    public static void main(String[] args){
        SpringApplication.run(ServiceApp.class,args);
    }

}
