package com.just.ytdemo.schedule;

import com.just.ytdemo.demoTest.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yutao.yan
 * @date 2018/11/28 18:12
 */
@Component
@Slf4j
public class TestJobDemo {


//    @Scheduled(cron = "1 * * * * ?")  //每分钟的第一秒执行
//    @Scheduled(cron = "* * 24 * * ?")  //每天24点执行
    @Scheduled(cron = "0 */1 * * * ?")  //每1min执行一次
//    @Scheduled(fixedDelay = 5000)         // 每5秒执行一次
    public void test() {
        Singleton.getInstance();
//        System.out.println("我进来了");
//        log.info(System.currentTimeMillis() + "");
    }


}
