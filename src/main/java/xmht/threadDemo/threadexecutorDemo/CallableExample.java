package xmht.threadDemo.threadexecutorDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) {
        // 创建一个 Callable 任务
        Callable<Integer> task = () -> {
            return 123;
        };

        Runnable task1=()->{
            System.out.println(1111);
        };
        // 创建一个单线程的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 提交 Callable 任务给线程池执行
        Future<Integer> future = executorService.submit(task);

        Executors.callable(task1);

        try {
            future.cancel(true);
            // 获取任务执行结果
            Integer result = future.get();
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executorService.shutdown();
    }
}