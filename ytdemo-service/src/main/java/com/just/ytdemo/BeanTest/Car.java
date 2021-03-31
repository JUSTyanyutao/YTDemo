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
//@Component
public class Car  implements BeanPostProcessor, InitializingBean , DisposableBean   {

    private Integer i = 0;

    private Long id;
    private String name;


    public Car() {
        i = i+1;
        System.out.println("car 构造器... order:" + i);
        this.name = "特斯拉";
    }

    public void initTest () {
        i = i+1;
        System.out.println("car ---  bean initMethod ---- order:" +i);
    }


    public void destoryTest() {
//        System.out.println("car ---  destory ---");
    }


    @Override
    public void destroy() throws Exception {
//        System.out.println("car-DisposableBean----destory---");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        i = i +1;
        System.out.println("car ---  postProcessBeforeInitialization ---- order:"+i);

        return bean;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        i = i+1;
        System.out.println("car ---- InitializingBean --- init--- order:"+i);
    }

    @PostConstruct
    public void init1 () {
        i = i +1;
        System.out.println("car ---  PostConstruct ---- order:"+i);
    }

    @PreDestroy
    public void destory1(){
//        System.out.println("car ---  destory1 ----");
    }



    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        i = i +1;
        System.out.println("car ---  postProcessAfterInitialization ---- order:"+i);
        return bean;
    }
}
