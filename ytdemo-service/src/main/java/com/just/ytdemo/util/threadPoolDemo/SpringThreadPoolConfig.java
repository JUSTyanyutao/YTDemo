package com.just.ytdemo.util.threadPoolDemo;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

public class SpringThreadPoolConfig {

//    /**
//     *  核心线程数
//     */
//    private int corePoolSize = 10;
//
//
//    /**
//     *  最大线程数
//     */
//    private int maximumPoolSize = 30;
//
//
//    /**
//     *  线程存活时间
//     */
//    private Long  keepAliveTime = 60L;
//
//    /**
//     *  时间单位
//     */
//    private TimeUnit timeUnit = TimeUnit.SECONDS;


    public static ThreadPoolTaskExecutor executor;


    /**
     *
     * spring的线程池
     * @return
     */
    public static ThreadPoolTaskExecutor  springTaskExecutor() {
        if (executor == null) {
            synchronized (ThreadPoolTaskExecutor.class) {
                if (executor == null) {
                    executor = new ThreadPoolTaskExecutor();
                    // 线程池维护线程的最少数量
                    executor.setCorePoolSize(10);
                    // 允许的空闲时间
                    executor.setKeepAliveSeconds(60);
                    // 线程池维护线程的最大数量
                    executor.setMaxPoolSize(60);
                    // 缓存队列
                    executor.setQueueCapacity(200);
                    executor.setThreadNamePrefix("springExecutor-");
                    // 对拒绝task的处理策略
                    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
                }
            }
        }
        return executor;
    }





}
