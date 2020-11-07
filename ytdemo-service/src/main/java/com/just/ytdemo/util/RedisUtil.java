package com.just.ytdemo.util;

import com.just.ytdemo.functionInterface.RunExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * https://www.cnblogs.com/jiangbei/p/8601107.html
 */
@Component
@Slf4j
public class RedisUtil {

    private static final long DEFAULT_EXPIRE_TIME = 10;

    @Autowired
    private StringRedisTemplate redisTemplate;



    public Integer getInt(String key) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return Integer.valueOf(value);
    }


    public void setInt(String key, Integer value) {
        redisTemplate.opsForValue().set(key,value.toString());
    }


    public String getString(String key) {
        String s = redisTemplate.opsForValue().get(key);
        return s;
    }

    public void setString(String key,String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 自增  值
     * @param key
     * @param delta
     * @return
     */
    public long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /** 设置过期时间  */
    public void resetTime(String key ,Long time, TimeUnit timeUnit) {
        redisTemplate.expire(key,time,timeUnit);
    }


    /**
     * 分布式锁
     * 大致思路就是：1、首选借助redis本身支持对应的setIfAbsent方法，
     * 该方法的特点是如果redis中已有该数据不保存返回false，不存该数据保存返回true；
     * 2、如果setIfAbsent返回true标识拿到同步锁，可进行操作，操作后并释放锁；
     * 3、如果没有通过setIfAbsent拿到数据，判断是否对锁设置了超时机制，没有设置判断是否需要继续等待；
     * 4、判断是否锁已经过期，需要对
     * (System.currentTimeMillis() > getLock(synKey) && (System.currentTimeMillis() > getSet(synKey, keepMills)))
     * 进行细细的揣摩一下，getSet可能会改变了其他人拥有锁的超时时间，但是几乎可以忽略；
     * 5、没有得到任何锁，判断继续等待还是退出。
     *
     * @param lock
     * @param executor
     */
    public void executeWithLock(String lock, RunExecutor executor) {

        if (redisTemplate.opsForValue().setIfAbsent(lock, Thread.currentThread().getName())) {
            try {
                redisTemplate.expire(lock, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                executor.execute();
            } finally {
                redisTemplate.expire(lock, 1, TimeUnit.SECONDS);
            }
        } else {
            Long expire = redisTemplate.getExpire(lock);
            if (expire == -1) {
                redisTemplate.expire(lock, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
            }
        }
    }


    private String getValue() {
        return String.format("%s#%s#%s", ProcessUtil.getIp(), ProcessUtil.getSystemProcessId(), Thread.currentThread().getName());
    }

    /**
     *
     * @param key
     */
    public boolean lock(String key) {
        try {
            String value = getValue();
            if (redisTemplate.opsForValue().setIfAbsent(key, value,DEFAULT_EXPIRE_TIME,TimeUnit.SECONDS)) {
                return true;
            } else if (value.equals(redisTemplate.opsForValue().get(key))) {
                redisTemplate.expire(key, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public void unlock(String key, String value) {
        try {
            String current = redisTemplate.opsForValue().get(key);
            if (current != null && current.equals(value)) {
                redisTemplate.delete(key);
            }
        } catch (Exception e) {
            log.error("redis unlock exception.", e);
        }
    }






}
