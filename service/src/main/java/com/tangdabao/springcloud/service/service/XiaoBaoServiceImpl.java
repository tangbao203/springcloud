package com.tangdabao.springcloud.service.service;

import com.tangdabao.springcloud.sdk.service.XiaoBaoService;
import com.tangdabao.springcloud.service.dao.XiaobaoDao;
import com.tangdabao.springcloud.service.redis.TangbaoRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class XiaoBaoServiceImpl implements XiaoBaoService {
    @Autowired
    private TangbaoRedis tangbaoRedis;
    @Autowired
    private XiaobaoDao xiaobaoDao;

    @Override
    public Map<String, Object> tangbaoIsMonster() {
        tangbaoRedis.write("我已经运行了tangbaoIsMonster这个方法");

        Map<String,Object> result=new HashMap<>();
        result.put("回答","你怕不怕我给你寄刀片～～～");
        result.put("redis运行位置",tangbaoRedis.read());
        return result;
    }

    @Override
    public Map<String, Object> whoIsTangbao() {
        tangbaoRedis.write("我已经运行了whoIsTangbao这个方法");

        Map<String,Object> result=new HashMap<>();
        result.put("redis运行位置",tangbaoRedis.read());

        List<Map> list=xiaobaoDao.ask();

        if(list==null)
            return result;

        for(Map item:list){
            result.put(item.get("askName").toString(),item.get("reply").toString());
        }

        return result;
    }
}
