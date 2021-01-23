package com.just.ytdemo.util.demoTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class redisTest {


    @Autowired
    private StringRedisTemplate redisTemplate;


    public void listTest(){

////        redisTemplate.opsForList().
//        redisTemplate.getConnectionFactory().getConnection().publish()
//        redisTemplate.boundListOps("yt:test:list").leftPush();

    }






    public static void main(String[] args) {





    }





}
