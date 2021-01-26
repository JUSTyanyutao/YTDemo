package com.just.ytdemo.util.demoTest;

import com.just.ytdemo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *
 * 分布式 高并发下  互联网企业  ID生成策略
 *
 */
@Component
public class UniqeIDUtil {


    @Autowired
    private RedisUtil redisUtil;

    /**
     * UUID
     * 唯一性   可读性差   没有递增性
     * @return
     */
    public String uuid() {
        return UUID.randomUUID().toString();
    }


    /**
     * 数据库  id自增
     *
     * 拓展性不好
     *
     * @return
     */
    public  String mysqlID(){
        return "";
    }


    /**
     * 雪花算法    满足每秒上万条创建  趋势递增
     * 67年  不会重复
     * 最大1ms    4096条
     * 41位时间戳 + 10为机器ID +
     * @return
     */
    public String snowID(){
        return "";
    }


    /**
     * redis 自增id
     *
     * 1min之内自增
     *
     * @return
     */
    public Long redisID(){
        return redisUtil.getId("yt:demo:id");
    }






}
