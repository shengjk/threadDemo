package xmht.threadDemo.threadexecutorDemo;

import java.util.concurrent.*;

public class CustomThreadPoolExecutorExample {

    public static void main(String[] args) {
        // 自定义线程工厂
        ThreadFactory customThreadFactory = new CustomThreadFactory("CustomThread");

        // 创建 ThreadPoolExecutor，传入自定义的 ThreadFactory
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // 核心线程数
                5, // 最大线程数
                10, // 线程空闲时间
                TimeUnit.SECONDS, // 时间单位
                new ArrayBlockingQueue<>(10), // 任务队列
                customThreadFactory, // 自定义 ThreadFactory
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );

        // 提交任务给线程池
        // 提交任务并获取 Future 对象
        Future<String> future = executor.submit(() -> {
            System.out.println("Task is running on thread: " + Thread.currentThread().getName());
            return "Task Result";
        });
        try {
            // 获取任务的执行结果
            String result = future.get();
            System.out.println("Task result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " is running...");
        });

        // 关闭线程池
        executor.shutdown();
        // 等待线程池关闭
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("线程池无法正常关闭");
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    // 自定义线程工厂实现
    static class CustomThreadFactory implements ThreadFactory {
        private final String threadNamePrefix;

        public CustomThreadFactory(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(threadNamePrefix + "-" + thread.getId());
            thread.setPriority(Thread.MAX_PRIORITY);
            return thread;
        }
    }
}