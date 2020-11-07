package com.just.ytdemo;

public class SubjectImpl implements Subject {

    @Override
    public String sayHello(String str) {
        System.out.println("str = hello:" + str);
        return str;
    }

    @Override
    public String sayBye(String str) {
        System.out.println("str = bye:" + str);
        return str;
    }
}
