package com.just.ytdemo.util.threadPoolDemo;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  多线程通信
 *
 *  无锁/不同的锁    可以用标志位  volatile  来控制 线程顺序
 *
 *  相同的锁    同一时刻 只能一个线程执行   只能 wait  notify  notifyAll  操作
 *
 *
 *
 */
public class ThreadTest {

    static  Integer num = 0;

    public static void threadTest1() {

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();

        Thread thread = new Thread() {
            @Override
            public void run() {
//                while (true) {
                    synchronized (ThreadTest.class) {

//                        try {
//                            sleep(1000);
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
                        System.out.println(1);
                        System.out.println(2);
                        if (num == 0) {
                            num = 1;
                        }
                        try {
                               sleep(1000);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                        }
//                        num = 0;
                        System.out.println(3);
                        if (num == 1) {
                            num = 0;
                        }

//                        if (num <= 99) {
//                            System.out.println("a");
//                            num  = num +1;
//                            num.notifyAll();
//                            for (int i = 0; i < 100; i++) {
//                                System.out.print("c -- ");
//                            }
//                        }  else {
//                            try {
//                                System.out.println("b  生产者 等待");
//                                num.wait();
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
                    }
                }
//            }
        };
        threadPoolExecutor.execute(thread);
    }

    public static void threadTest2() {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();
            Thread thread = new Thread() {
                @Override
                public void run() {



                    while (true) {
                        synchronized (num) {

//                            try {
//                                sleep(1000);
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
                            System.out.println( "num -- : "  + num  );
                            if (num == 1) {
                                System.out.println(" d -- " );
                                break;
                            }
                        }
                    }
                }
            };
            threadPoolExecutor.execute(thread);
        }


    /**
     * 生产者
     */
    public static void shengchanTest() {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (ThreadTest.class) {
                    while (true) {



                        while (num >= 10) {
                            try {
                                ThreadTest.class.wait();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        num =  num + 1;
                        System.out.println("生产鸡蛋 ： num :" + num);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ThreadTest.class.notify();
                    }

                }
            }
        };
        threadPoolExecutor.execute(thread);
//        thread.start();
    }




    /**
     * 消费者
     */
    public static void xiaofeiTest() {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (ThreadTest.class) {
                    while (true) {
                        while (num < 1) {
                            try {
                                ThreadTest.class.wait();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        num = 0;
                        System.out.println("取出鸡蛋 ： num：" + num);
                        ThreadTest.class.notify();
                    }

                }
            }
        };
//        thread.start();

        threadPoolExecutor.execute(thread);
    }




    public static void main(String[] args)
            throws InterruptedException {
        ThreadTest syn= new ThreadTest();
//        threadTest2();
//        threadTest1();
        shengchanTest();
        xiaofeiTest();



    }






}
