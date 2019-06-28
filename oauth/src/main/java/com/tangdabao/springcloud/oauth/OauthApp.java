package com.tangdabao.springcloud.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


@EnableEurekaClient
@SpringBootApplication
@EnableAuthorizationServer
@MapperScan("com.tangdabao.springcloud.oauth.dao")
public class OauthApp {
    public static void main(String[] args){
        SpringApplication.run(OauthApp.class,args);
    }
}
