package com.tangdabao.springcloud.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.tangdabao.springcloud.service.dao")
public class ServiceApp {
    public void mian(String[] arags){
        SpringApplication.run(ServiceApp.class,arags);
    }

}
