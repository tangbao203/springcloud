package com.tangdabao.springcloud.sdk.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(name="${service.name}")
@RequestMapping("/xiaobao")
public interface XiaoBaoService {

    @RequestMapping("/tangbaoIsMonster")
    Map<String,Object> tangbaoIsMonster();

    @RequestMapping("/whoIsTangbao")
    Map<String,Object> whoIsTangbao();
}
