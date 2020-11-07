package com.just.ytdemo.demoTest;


import lombok.Synchronized;

/**
 * @author yutao.yan
 * @date 2019/1/14 16:40
 */
public  class Singleton {

    private static volatile Object object;

    private Singleton(){}

    //Double-Check
    public static Object getInstance() {
        if (object == null) {
            synchronized(Singleton.class) {
                if (object == null) {
                    object = new Object();
                }
            }
        }
        return object;
    }



    public double getResult(Integer a,Integer b,String opera) {

        double result = 0;
        if (opera.equals("+")) {
            result = a + b;
        } else if ( opera.equals("-")) {
            result = a-b;
        } else if (opera.equals("*")) {
            result = a*b;
        } else if (opera.equals("/")) {
            result = a/b;
        }
        return result;
    }











}



