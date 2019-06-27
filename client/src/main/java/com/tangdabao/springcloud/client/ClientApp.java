package com.tangdabao.springcloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.tangdabao.springcloud.sdk.service"})
@SpringBootApplication
public class ClientApp {
    public static void main(String[] args){
        SpringApplication.run(ClientApp.class,args);
    }
}
