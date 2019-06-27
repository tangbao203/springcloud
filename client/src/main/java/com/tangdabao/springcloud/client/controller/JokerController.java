package com.tangdabao.springcloud.client.controller;

import com.tangdabao.springcloud.sdk.service.XiaoBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/joker")
@RestController
public class JokerController {
    @Autowired
    private XiaoBaoService xiaoBaoService;

    @RequestMapping("/tangbaoIsMonster")
    public Object tangbaoIsMonster(){
        Map<String,Object> result=xiaoBaoService.tangbaoIsMonster();
        //bu la bu la.......
        return result;
    }
}
