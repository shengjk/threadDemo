package xmht.threadDemo.threadexecutorDemo;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {

        ThreadFactory factory = runnable -> {
            Thread thread = new Thread(runnable);
            thread.setUncaughtExceptionHandler((t, e) -> {
                System.err.println("Caught exception: " + e.getMessage());
            });
            return thread;
        };

        // 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, // corePoolSize
            5, // maximumPoolSize
            60, // keepAliveTime
            TimeUnit.SECONDS, // time unit
            new LinkedBlockingQueue<>(), // work queue
                factory,
            new ThreadPoolExecutor.AbortPolicy() // rejection handler
        );

        // 提交任务
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is being processed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // 模拟任务执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        executor.execute(()->{
            throw new RuntimeException("test");
        });

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            System.err.println("Uncaught exception in thread: " + thread.getName());
            System.err.println("Exception message: " + throwable.getMessage());
        });

        // 关闭线程池
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // 强制关闭
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All tasks completed.");
    }
}