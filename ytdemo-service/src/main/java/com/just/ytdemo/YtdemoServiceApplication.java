package com.just.ytdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;



@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAspectJAutoProxy
@MapperScan("com.just.ytdemo.dao.mapper")
@ImportResource(locations = {"classpath*:dubboProvider.xml"})
public class YtdemoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtdemoServiceApplication.class, args);
    }
}
