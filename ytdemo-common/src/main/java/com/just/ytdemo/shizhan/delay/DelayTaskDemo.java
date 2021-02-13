package com.just.ytdemo.shizhan.delay;


/**
 * 延时任务
 *
 * 应用场景  订单超时30min自动关闭
 *
 * 1、轮询数据库
 *
 * 2、jdk 延迟队列  DelayQueue 无界阻塞
 *  poll()  得到超时的元素  如果没有超时的元素 则返回null
 *  take()  得到超时的元素  如果没有超时的元素 则阻塞
 *
 *
 */
public class DelayTaskDemo {


}
