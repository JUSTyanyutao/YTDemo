package com.just.ytdemo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ImportResource(locations = {"classpath*:dubboConsumer.xml"})
public class YtdemoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(YtdemoWebApplication.class, args);
	}
}
