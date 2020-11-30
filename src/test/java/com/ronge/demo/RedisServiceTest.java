package com.ronge.demo;

import com.ronge.demo.service.RedisService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class RedisServiceTest {

  /* @Autowired
    private RedisService redisService;

    @Before
    public void set() {
        redisService = new RedisService();
    }


    public void test(){
        String key="name";
        String value="ronge";
        redisService.set(key,value);

       assertEquals("测试失败，两个值不相等",value, redisService.get(key));
    }*/

}