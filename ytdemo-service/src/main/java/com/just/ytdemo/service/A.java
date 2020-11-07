package com.just.ytdemo.service;

import java.math.BigDecimal;

/**
 * @author yutao.yan
 * @date 2018/12/3 18:05
 */
public class A {

    public  Object object = null;


    public static void main(String[] args) {
//        A a = new A();
//        A b = new A();
//
//        a.object = b;
//        b.object = a;
//
//        b = null;
//        a = null;
//
//        System.gc();


//        int a = 0;
//        int i = a++;
//        System.out.println(a);
//        System.out.println(i);

        String s = new BigDecimal("100.01").stripTrailingZeros().toPlainString();
        System.out.println(s);

    }

}
