package com.just.ytdemo;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyTest   {


    public final  static Map map = new HashMap();


    public static void main(String[] args) throws IllegalArgumentException{


        Subject subject = new SubjectImpl();
        ProxyTool proxyTool = new ProxyTool(subject);

        Subject object = (Subject) Proxy.
                newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(),proxyTool);

        object.sayHello("fly");

    }
}
