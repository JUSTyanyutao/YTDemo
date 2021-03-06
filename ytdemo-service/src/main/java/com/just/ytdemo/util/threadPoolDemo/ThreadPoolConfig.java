package com.just.ytdemo.util.threadPoolDemo;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ThreadPoolConfig  {

    /**
     *  核心线程数
     */
    private static int corePoolSize = 10;


    /**
     *  最大线程数
     */
    private static int maximumPoolSize = 30;


    /**
     *  线程存活时间
     */
    private static Long  keepAliveTime = 60L;

    /**
     *  时间单位
     */
    private static TimeUnit timeUnit = TimeUnit.SECONDS;


    private static ThreadPoolExecutor threadPoolExecutor;

    /**
     * common 线程池配置
     * 排队有三种通用策略：
     * 1、直接提交。工作队列的默认选项是 SynchronousQueue，它将任务直接提交给线程而不保持它们。在此，如果不存在可用于立即运行任务的线程，
     * 则试图把任务加入队列将失败，因此会构造一个新的线程。此策略可以避免在处理可能具有内部依赖性的请求集时出现锁。直接提交通常要求无界
     * maximumPoolSizes 以避免拒绝新提交的任务。当命令以超过队列所能处理的平均数连续到达时，此策略允许无界线程具有增长的可能性。
     * 2、无界队列。使用无界队列（例如，不具有预定义容量的 LinkedBlockingQueue）将导致在所有 corePoolSize 线程都忙时新任务在队列中
     * 等待。这样，创建的线程就不会超过 corePoolSize。（因此，maximumPoolSize 的值也就无效了。）
     * 当每个任务完全独立于其他任务，即任务执行互不影响时，适合于使用无界队列；例如，在 Web 页服务器中。
     * 这种排队可用于处理瞬态突发请求，当命令以超过队列所能处理的平均数连续到达时，此策略允许无界线程具有增长的可能性。
     * 3、有界队列。当使用有限的 maximumPoolSizes 时，有界队列（如 ArrayBlockingQueue）有助于防止资源耗尽，
     * 但是可能较难调整和控制。队列大小和最大池大小可能需要相互折衷：使用大型队列和小型池可以最大限度地降低 CPU 使用率、
     * 操作系统资源和上下文切换开销，但是可能导致人工降低吞吐量。如果任务频繁阻塞（例如，如果它们是 I/O 边界），
     * 则系统可能为超过您许可的更多线程安排时间。使用小型队列通常要求较大的池大小，CPU 使用率较高，但是可能遇到不可接受的调度开销，这样也会降低吞吐量。
     *
     *
     */
    public static ThreadPoolExecutor commonTaskExecutor() {
        if (threadPoolExecutor == null) {
            synchronized (ThreadPoolExecutor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                                                                maximumPoolSize,
                                                                keepAliveTime,
                                                                timeUnit,
                                                                new LinkedBlockingQueue(20));
                }
            }
        }
        return threadPoolExecutor;
    }


}
