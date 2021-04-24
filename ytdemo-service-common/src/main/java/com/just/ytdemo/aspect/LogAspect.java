package com.just.ytdemo.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * 日志切面类 Aspect
 *
 * 前置通知 @before
 * 后置通知 @after
 * 返回通知 @AfterReturning
 * 异常通知 @AfterThrowing
 * 环绕通知 @After
 *
 */
@Aspect
@Component
public class LogAspect {


    /**
     * 抽取公共的切入点表达式
     * 1、本类引用
     * 2、其他的切面引用
     */
    @Pointcut("execution(* com.just.ytdemo.export..*.*(..))")
    public void pointCut(){}

    /**
     * 前置通知
     */
    @Before("pointCut()")
    public void beforeLog(){
        System.out.println("before log");
    }

    /**
     * 后置通知
     */
    @After("pointCut()")
    public void afterLog(){
        System.out.println("after log");
    }


    /**
     * 环绕通知
     */
    @Around("pointCut()")
    public void aroundLog(){
        System.out.println("around log");
    }


}
