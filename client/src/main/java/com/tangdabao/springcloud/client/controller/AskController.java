package com.tangdabao.springcloud.client.controller;

import com.tangdabao.springcloud.sdk.service.XiaoBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/ask")
@RestController
public class AskController {
    @Autowired
    private XiaoBaoService xiaoBaoService;

    @RequestMapping("whoIsTangbao")
    public Object whoIsTangbao(){
        System.out.println("=========我在这里==========");

        Map<String,Object> result=xiaoBaoService.whoIsTangbao();

        return result;
    }
}
