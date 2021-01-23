package com.just.ytdemo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.just.ytdemo.dao.mapper")
@ImportResource(locations = {"classpath*:dubboProvider.xml"})
public class YtdemoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtdemoServiceApplication.class, args);
    }
}
