package com.chenbro.user.test;

import com.chenbro.BarcodeUserApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/29 10:08
 * @Version 1.0
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BarcodeUserApplication.class)
public class RedisTest {

//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Test
//    public void testRedis() {
//        // 存储数据
//        redisTemplate.opsForValue().set("name", "lavine");
//        // 获取数据
//        String name = redisTemplate.opsForValue().get("name");
//        System.out.println(name);
//    }
//
//    @Test
//    public void testRedis2() {
//        // 存储数据并设置过期时间
//        redisTemplate.opsForValue().set("hobby", "travel", 10, TimeUnit.SECONDS);
//        // 获取数据
//        String name = redisTemplate.opsForValue().get("name");
//        System.out.println(name);
//    }
//
//    @Test
//    public void testHash() {
//        BoundHashOperations<String, Object, Object> user = redisTemplate.boundHashOps("user");
//        // 操作hash 数据
//        user.put("name", "jack");
//        user.put("age", "22");
//
//        // 获取单个数据
//        Object name = user.get("name");
//        System.out.println("name:" + name);
//        // 获取所有数据
//        Map<Object, Object> entries = user.entries();
//        for (Map.Entry<Object, Object> me : entries.entrySet()) {
//            System.out.println(me.getKey() + ":" + me.getValue());
//        }
//    }
//
//    @Test
//    public void testRandom() {
//        int min = Double.valueOf(Math.pow(10, 6 - 1)).intValue();
//        System.out.println(min);
//        System.out.println(Double.valueOf(Math.pow(10, 6 + 1)).intValue() - 1);
//    }
}
