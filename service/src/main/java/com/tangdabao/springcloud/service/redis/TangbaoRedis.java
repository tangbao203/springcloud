package com.tangdabao.springcloud.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TangbaoRedis {
    @Autowired
    private RedisTemplate redisTemplate;

    private final String KEY="tangdabao";

    public void write(String content){
        redisTemplate.opsForValue().set(KEY,content);
    }

    public String read(){
        return redisTemplate.opsForValue().get(KEY).toString();
    }
}
