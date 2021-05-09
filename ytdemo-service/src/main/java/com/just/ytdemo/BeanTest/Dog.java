package com.just.ytdemo.BeanTest;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class Dog implements  InitializingBean , DisposableBean {

    private Integer i = 0;

    private Long id;
    private String name;


    public Dog() {
        i = i+1;
        System.out.println("dog 构造器... order:" + i);
    }


    @Override
    public void destroy() throws Exception {
//        System.out.println("dog-DisposableBean----destory---");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        i = i+1;
        System.out.println("dog ---- InitializingBean --- init--- order:"+i);
    }

    @PostConstruct
    public void init1 () {
        i = i +1;
        System.out.println("dog ---  PostConstruct ---- order:"+i);
    }

    @PreDestroy
    public void destory1(){
//        System.out.println("dog ---  destory1 ----");
    }
}
