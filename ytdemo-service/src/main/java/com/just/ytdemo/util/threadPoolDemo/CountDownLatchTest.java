package com.just.ytdemo.util.threadPoolDemo;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

public class CountDownLatchTest {


    /***
     *   https://zhuanlan.zhihu.com/p/95835099
     *
     * CountDownLatch可以使一个获多个线程等待其他线程各自执行完毕后再执行。
     * CountDownLatch 定义了一个计数器，和一个阻塞队列，
     * 当计数器的值递减为0之前，阻塞队列里面的线程处于挂起状态，
     * 当计数器递减到0时会唤醒阻塞队列所有线程，这里的计数器是一个标志，
     * 可以表示一个任务一个线程，也可以表示一个倒计时器，
     * CountDownLatch可以解决那些一个或者多个线程在执行之前必须依赖于某些必要的前提业务先执行的场景。
     *
     *
     * 原理 AQS 同步队列    state   ==   计数
     *  await()   把当前线程放进 阻塞队列中
     *  countDown()   state - 1 (锁释放)  /  计数器 - 1             CAS自旋   -1
     *  当state == 0  阻塞队列节点线程全部释放
     *
     */




    /**
     * 让某个线程等待
     * */
    public static ArrayList threadTest1(ArrayList<Integer> list, CountDownLatch latch  ) {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (ThreadPoolTest.class) {
                for (int i=0; i<30; i++) {
                    System.out.println("count:" + list.get(0) + "\t" + Thread.currentThread().getName());
                    list.set(0, list.get(0) + 1);
                    }
                }
                latch.countDown();
            }
        };
        threadPoolExecutor.execute(thread);
        return list;
    }



    /**
     * 线程2
     * */
    public static ArrayList threadTest2(ArrayList<Integer> list, CountDownLatch latch  )  {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();
        Thread thread = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("线程3  我进来了,需要等待线程1 线程2 执行完");
                latch.await();
                System.out.println("线程3  等待结束");
//                synchronized (ThreadPoolTest.class) {
                    for (int i=0; i<30; i++) {
                        System.out.println("count:" + list.get(0) + "\t" + Thread.currentThread().getName());
                        list.set(0, list.get(0) + 1);
                    }
//                }
            }
        };
        threadPoolExecutor.execute(thread);
        return list;
    }



    public static void main(String[] args) throws InterruptedException {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        CountDownLatch latch=new CountDownLatch(2);//两个工人的协作
        list = threadTest1(list,latch);
        list = threadTest1(list,latch);
        threadTest2(list,latch);
        latch.await();
        System.out.println("---------------------------main运行结束-----------2---------------------" + list.get(0));
    }


}
