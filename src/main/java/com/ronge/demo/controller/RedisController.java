package com.ronge.demo.controller;

import com.ronge.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;


    @RequestMapping("/redis/set")
    public void set(){
        redisService.set("name","ronge!!!");
    }

    @RequestMapping("/redis/get")
    public void get(){
        String value=(String) redisService.get("name");
        System.out.println(value);
    }
}
