package com.just.ytdemo.util.springTest;

import com.just.ytdemo.YtdemoServiceApplication;
import com.just.ytdemo.config.AppBeanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

//@ComponentScan(value = "com.just.ytdemo.service")
public class SpringTest {

    /**
     *
     *      单实例bean  默认bean  随着容器初始化 而初始化
     *      多实例bean  使用的时候 初始化
     *
     *
     *     1、注解@Controller   @service   @component
     *     2、@Bean
     *     3、@import  导入
     *     4、使用factoryBean对象  实现factoryBean接口  工厂bean
     *
     *
     *
     *
     */



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
