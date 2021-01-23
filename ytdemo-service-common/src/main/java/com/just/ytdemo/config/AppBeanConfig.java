package com.just.ytdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yutao.yan
 * @date 2018/11/9 14:18
 */
@Configuration
public class AppBeanConfig {


    /**
     * common 线程池配置
     *
     *    //    使用的时候
     *         //@Autowired
     *         //@Qualifier("commonTaskExecutor")
     *         //ThreadPoolTaskExecutor commonTaskExecutor;
     *
     */
    @Bean(name = "commonTaskExecutor")
    public ThreadPoolTaskExecutor commonTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程池维护线程的最少数量
        executor.setCorePoolSize(10);
        // 允许的空闲时间
        executor.setKeepAliveSeconds(180);
        // 线程池维护线程的最大数量
        executor.setMaxPoolSize(150);
        // 缓存队列
        executor.setQueueCapacity(400);
        executor.setThreadNamePrefix("commonTaskExecutor-");
        // 对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
















}
