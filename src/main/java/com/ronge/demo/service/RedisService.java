package com.ronge.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    public void set(String key,String value){
        ValueOperations<String,Object> valueOperations=redisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    public Object get(String key){
        ValueOperations<String,Object> valueOperations=redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

}
