package xmht.threadDemo.threadexecutorDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
正式线上环境，建议线程池的队列是有限的
线程池监控
 */
public class MonitoringThreadPool extends ThreadPoolExecutor {

    public MonitoringThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        // 添加监控逻辑：任务执行前
        System.out.println("Task is about to execute: " + t.getName());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        // 添加监控逻辑：任务执行后
        System.out.println("Task finished executing: " + r.toString());
    }

    @Override
    protected void terminated() {
        super.terminated();
        // 添加监控逻辑：线程池关闭前
        System.out.println("Thread pool is about to terminate.");
    }

    public static void main(String[] args) {
        // 创建 MonitoringThreadPool 实例
        MonitoringThreadPool threadPool = new MonitoringThreadPool(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        // 提交任务给线程池
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            threadPool.execute(() -> System.out.println("Task " + taskId + " is running."));
        }

        // 关闭线程池
        threadPool.shutdown();
    }
}