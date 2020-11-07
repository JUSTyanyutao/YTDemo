package com.just.ytdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *  代理类  aop
 *
 *  jdk动态代理
 *
 */
public class ProxyTool implements InvocationHandler {


    public Subject target;

    public ProxyTool(Subject target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("proxy begin --------");

        Object result = method.invoke(target,args);

        System.out.println("proxy end --------");
        return result;
    }
}
