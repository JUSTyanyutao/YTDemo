package com.just.ytdemo.BeanTest;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * 按照条件去注册
 *
 *
 */
public class ChinaPersonCondition implements Condition {
    /**
     *
     * @param conditionContext          上下文环境
     * @param annotatedTypeMetadata     注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        //  获取环境变量
        Environment env = conditionContext.getEnvironment();
        System.out.println(env.getProperty("os.name"));
        if (env.getProperty("os.name").contains("Windows")) {
            return true;
        }
        // 获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
//        System.out.println(classLoader.);

        // 获取ioc使用的beanFactory
        conditionContext.getBeanFactory();

        //获取bean定义的注册类
        BeanDefinitionRegistry   beanDefinitionRegistry =  conditionContext.getRegistry();
        // 容器中包含 person的类
        if (beanDefinitionRegistry.containsBeanDefinition("person")) {

        }

        return false;
    }
}
