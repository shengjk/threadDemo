package xmht.threadDemo.threadexecutorDemo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 提交多个任务
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executorService.submit(() -> {
                System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
             });
        }

        // 关闭线程池
        List<Runnable> runnables = executorService.shutdownNow();
        for (Runnable runnable : runnables) {
            System.out.println(runnable.toString());
        }
        System.out.println("All tasks submitted.");
    }
}
