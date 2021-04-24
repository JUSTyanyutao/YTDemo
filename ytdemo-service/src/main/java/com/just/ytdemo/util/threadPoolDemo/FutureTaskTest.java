package com.just.ytdemo.util.threadPoolDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {



    public static void test() throws ExecutionException, InterruptedException {

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(6000);
                return "null";
            }
        };


        FutureTask task = new FutureTask(callable);
        task.run();
        System.out.println("结果:"+task.get());

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test();

        System.out.println("结束");

    }

}
