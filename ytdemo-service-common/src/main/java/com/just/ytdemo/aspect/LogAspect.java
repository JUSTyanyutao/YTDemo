package com.just.ytdemo.aspect;

import com.alibaba.fastjson.JSON;
import javafx.scene.paint.Stop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


/**
 * 日志切面类 Aspect
 *
 * 前置通知 @before
 * 后置通知 @after
 * 返回通知 @AfterReturning
 * 异常通知 @AfterThrowing
 * 环绕通知 @After
 *
 * 【AOP 原理   给容器中注册了什么组件  这个组件何时做了什么 】
 *
 *   @EnableAspectJAutoProxy
 *   @Import({AspectJAutoProxyRegistrar.class})
 *
 *   注册enableAspectJAutoProxy =   AnnotationAwareAspectJAutoProxyCreator
 *   给容器创建一个   AnnotationAwareAspectJAutoProxyCreator   注解切面代理创建器
 *                      AspectJAwareAdvisorAutoProxyCreator
 *                          AbstractAdvisorAutoProxyCreator
 *                              AbstractAutoProxyCreator
 *   implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *
 *   关注后置处理器  BeanPostProcessor 以及  自动装配BeanFactory
 *
 *    AbstractAutoProxyCreator.setBeanFactory
 *    AbstractAutoProxyCreator.postProcessBeforeInstantiation   后置处理器的逻辑
 *
 *    AbstractAdvisorAutoProxyCreator.setBeanFactory   ------>   initBeanFactory()
 *
 *    AnnotationAwareAspectJAutoProxyCreator ----->  initBeanFactory()
 *
 *
 *
 *      postProcessBeforeInstantiation
 *      每一个bean创建前，调用postProcessBeforeInstantiation
 *
 *      调用postProcessAfterInitialization   ----->   wrapIfNecessary  ----- >   createProxy  创建代理类
 *
 *      DefaultAopProxyFactory   ---- >  createAopProxy  创建代理对象  spring自行决定
 *          JdkDynamicAopProxy   jdk代理      ----->          impl    InvocationHandler
 *          ObjenesisCglibAopProxy    cglib代理     ------>   extends CglibAopProxy
 *
 *          以后容器中获取到的就是这个容器的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 *
 *
 *       目标方法执行:
 *       CglibAopProxy ---->  DynamicAdvisedInterceptor ---->  intercept
 *
 *      如果有拦截器量   ReflectiveMethodInvocation ->     proceed()
 *
 *
 *
 *
 *
 *  @see org.springframework.aop.interceptor.ExposeInvocationInterceptor
 *
 *
 *
 *
 */
@Slf4j
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

//    /**
//     * 前置通知
//     */
//    @Before("pointCut()")
//    public void beforeLog(){
//        System.out.println("before log");
//    }
//
//    /**
//     * 后置通知
//     */
//    @After("pointCut()")
//    public void afterLog(){
//        System.out.println("after log");
//    }
//
//
//    /**
//     * 返回通知
//     */
//    @AfterReturning("pointCut()")
//    public void afterReturnLog(){
//        System.out.println("afterReturn log");
//    }


    /**
     * 环绕通知
     */
    @Around("pointCut()")
    public Object aroundLog(ProceedingJoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();
        String argsBuilder = getString(args);

        // 自定义注解
        if (joinPoint.getSignature() instanceof MethodSignature) {
        }

        log.info("{\"class\":"  + joinPoint.getTarget().getClass().getSimpleName()  +
                "\"method\":"  + joinPoint.getSignature().getName()  +
                "\"params\":}" +  argsBuilder  +
                "}");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj;
        try {
            obj = joinPoint.proceed();
            String response = null ;
            if (obj != null) {
                response = getString(obj);
            }

            stopWatch.stop();
            log.info(
                    "{\"class\":" + joinPoint.getTarget().getClass().getSimpleName() +
                    "\"method\":" + joinPoint.getSignature().getName() +
                    ",\"input\":" + argsBuilder +
                    ",\"output\":" + response +
                    ",\"cost\":" + (stopWatch.getTotalTimeMillis()) +
                    "}");
            return obj;
        } catch (Throwable throwable) {
            log.error(
                    "{\"class\":" + joinPoint.getTarget().getClass().getSimpleName() +
                            "\"method\":" + joinPoint.getSignature().getName() +
                            ",\"input\":" + argsBuilder +
                            ",\"cost\":" + (stopWatch.getTotalTimeMillis()) +
                            "}");
            return "系统异常";
        }

    }


    private String getString(Object arg) {
        String s;
        try {
            s = JSON.toJSONString(arg);
        } catch (Exception e) {
            s = "can't parse to JSON, " + e.getMessage();
        }
        if (s.length() > 550) {
            s = String.format("%s....{%s}....%s", s.substring(0, 250), s.length(), s.substring(s.length() - 250, s.length()));
        }
        return s;
    }

}
