package com.tangdabao.springcloud.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableAuthorizationServer
public class OauthApp {
    public void main(String[] args){
        SpringApplication.run(OauthApp.class,args);
    }
}
