package com.just.ytdemo.BeanTest;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@Component
public class Person implements  InitializingBean , DisposableBean , ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Integer i = 0;

    private Long id;
    private String name;


    public Person() {
        i = i+1;
        System.out.println("person 构造器... order:" + i);
    }


    @Override
    public void destroy() throws Exception {
//        System.out.println("dog-DisposableBean----destory---");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        i = i+1;
        System.out.println("person ---- InitializingBean --- init--- order:"+i);
    }

    @PostConstruct
    public void init1 () {
        i = i +1;
        System.out.println("person ---  PostConstruct ---- order:"+i);
    }

    @PreDestroy
    public void destory1(){
//        System.out.println("dog ---  destory1 ----");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
