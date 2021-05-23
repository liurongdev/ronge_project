package com.ronge.demo;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {
   // private ReentrantLock lock = new ReentrantLock();

    @Test
    public void contextLoads() throws Exception {
        System.out.println(Thread.currentThread().getContextClassLoader());
        ConcurrentHashMap<String,String> concurrentHashMap=new ConcurrentHashMap();
        concurrentHashMap.put("name","ronge");
        System.out.println(concurrentHashMap.get("name"));
    }
}
