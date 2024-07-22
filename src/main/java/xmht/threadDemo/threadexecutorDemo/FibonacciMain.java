package xmht.threadDemo.threadexecutorDemo;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class FibonacciTask extends RecursiveTask<Integer> {
    private final int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        } else {
            FibonacciTask task1 = new FibonacciTask(n - 1);
            FibonacciTask task2 = new FibonacciTask(n - 2);
            task1.fork(); // 异步执行第一个子任务
            return task2.compute() + task1.join(); // 执行第二个子任务并等待第一个子任务完成
        }
    }
}

public class FibonacciMain {
    public static void main(String[] args) {
        int n = 10; // 计算斐波那契数列的第n项

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FibonacciTask fibonacciTask = new FibonacciTask(n);
        int result = forkJoinPool.invoke(fibonacciTask);

        System.out.println("Fibonacci number at position " + n + " is: " + result);
    }
}