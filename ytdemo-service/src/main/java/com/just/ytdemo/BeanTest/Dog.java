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
@Component
public class Dog implements  InitializingBean , DisposableBean ,BeanPostProcessor  {

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


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        i = i +1;
        System.out.println("dog ---  postProcessBeforeInitialization ---- order:"+i);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        i = i +1;
        System.out.println("dog ---  postProcessAfterInitialization ---- order:"+i);

        return bean;
    }
}
