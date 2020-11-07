package com.just.ytdemo.util.threadPoolDemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {


    /** 单一线程 */
    public static void singleThreadTest() {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();


        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
    }

    /** 多线程 */
    public static void threadTest() {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();
        for (int i=0; i<100; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }

    /** 多线程 不同步 */
    public static void threadTest1() {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i=0; i<100; i++) {
            threadPoolExecutor.execute( () -> {
                System.out.println("count:" + list.get(0) + "\t" + Thread.currentThread().getName());
                list.set(0, list.get(0) + 1);
            });

//                    new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("count:" + list.get(0) +"\t" + Thread.currentThread().getName());
//                    list.set(0,list.get(0) + 1);
//                }
//            });
        }
    }

    /** 多线程 同步
     *
     * synchronized 不常用  本地单机业务逻辑可以用
     * https://blog.csdn.net/xiao__gui/article/details/8188833
     *
     * 分布式 都用分布式锁   具体看redisTool里面的   redis分布式锁   也有 zk 分布式锁
     * */
    public static void threadTest2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();


        for (int i=0; i<30; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    synchronized (ThreadPoolTest.class) {
                        System.out.println("count:" + list.get(0) +"\t" + Thread.currentThread().getName());
                        list.set(0,list.get(0) + 1);
                    }
                }
            };
            threadPoolExecutor.execute(thread);
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        System.out.println("-----------------------------------------------------------");

//        for (int i=0; i<30; i++) {
//            threadPoolExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    synchronized (ThreadPoolTest.class) {
//                        System.out.println("count:" + list.get(0) +"\t" + Thread.currentThread().getName());
//                        list.set(0,list.get(0) + 1);
//                    }
//                }
//            });
//        }


    }




    /**
     * 让几个线程有序执行
     *
     * */
    public static ArrayList threadTest3(ArrayList<Integer> list,CountDownLatch latch  ) {

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();
        Thread thread = new Thread() {
            @Override
            public void run() {
//                synchronized (ThreadPoolTest.class) {
                    for (int i=0; i<30; i++) {
                        System.out.println("count:" + list.get(0) + "\t" + Thread.currentThread().getName());
                        list.set(0, list.get(0) + 1);
//                    }
                }
                latch.countDown();
            }
        };

        threadPoolExecutor.execute(thread);


//        awaitTerminationQuietly(threadPoolExecutor);

//        thread.start();

//        System.out.println("---------------------------main-----------1---------------------");

//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
////                synchronized (ThreadPoolTest.class) {
//                for (int i=0; i<30; i++) {
//                    System.out.println("count:" + list.get(0) + "\t" + Thread.currentThread().getName());
//                    list.set(0, list.get(0) + 1);
////                    }
//                }
//            }
//        };
//        threadPoolExecutor.execute(thread1);



//        threadPoolExecutor.shutdown();
//        thread1.start();


//        try {
//            boolean loop = true;
//            do {    //等待所有任务完成
//                loop = !threadPoolExecutor.awaitTermination(2, TimeUnit.SECONDS);  //阻塞，直到线程池里所有任务结束
//            } while(loop);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        return list;


    }


    /**
     * 让几个线程有序执行
     *
     * */
    public static void threadTest4() throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        CountDownLatch latch=new CountDownLatch(2);//两个工人的协作

        list = threadTest3(list,latch);
        list = threadTest3(list,latch);

        latch.await();


        System.out.println("---------------------------main运行结束-----------2---------------------" + list.get(0));

    }



    public static void main(String[] args) throws InterruptedException {

        String s = "11052";
        String b = "110"+ new String("52");

        System.out.println(s.equals(b) );
        System.out.println(s == b);
//        threadTest4();
//        threadTest3();
//        System.out.println(Thread.currentThread().getName());
//        ArrayList<Integer> count = new ArrayList<>();
//        count.add(0);
//        ThreadPoolExecutor threadPoolExecutor = ThreadPoolConfig.commonTaskExecutor();
//        for (int i = 0; i<10000 ; i++) {
//            threadPoolExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("count:" + count.get(0) +"\t" + Thread.currentThread().getName());
//                    count.set(0,count.get(0) + 1);
////                    System.out.println(Thread.currentThread().getName());
//                }
//            });
//        }





    }
}
