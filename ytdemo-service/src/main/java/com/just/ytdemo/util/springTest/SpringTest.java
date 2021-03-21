package com.just.ytdemo.util.springTest;

import com.just.ytdemo.YtdemoServiceApplication;
import com.just.ytdemo.config.AppBeanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@ComponentScan(value = "com.just.ytdemo.service")
public class SpringTest {



    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringTest.class);
        ConfigurableEnvironment  env =   context.getEnvironment();
        System.out.println(env);

        Object obj = context.getBean("beanColorFctory");
        System.out.println(obj.getClass());

//        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
        String[]  names = context.getBeanDefinitionNames();
        for (String str : names) {
            System.out.println(str);
        }


    }



}
