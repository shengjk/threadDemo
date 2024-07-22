package xmht.threadDemo.threadexecutorDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @author shengjk1
 * @date 7/1/2024
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Executors.newSingleThreadScheduledExecutor();
        ScheduledThreadPoolExecutor scheduledExecutorService1 = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledExecutorService2 = Executors.newScheduledThreadPool(1);

        Future future = new FutureTask<>(() -> {
            System.out.println("aaa");
        }, 1);
        ExecutorService executorService = Executors.newCachedThreadPool();

        Executors.newSingleThreadExecutor();


        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                Integer element = 1;
                queue.put(element);
                System.out.println("Produced: " + element);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer element = queue.take();
                System.out.println("Consumed: " + element);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();


        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduledExecutorService3 = Executors.newScheduledThreadPool(1);
        ExecutorService executorService2 = Executors.newWorkStealingPool(1);


        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();
        atomicInteger.compareAndSet(1, 1);
        atomicInteger.accumulateAndGet(10, (left, right) -> left + right);

        AtomicLong atomicLong = new AtomicLong();
        AtomicBoolean atomicBoolean = new AtomicBoolean();

        AtomicLong count = new AtomicLong(10); // 初始化值为 10


        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        AtomicIntegerArray atomicIntArray = new AtomicIntegerArray(5);
        AtomicLongArray atomicLongArray = new AtomicLongArray(10);
        // 使用索引初始化数组的值
        atomicLongArray.set(0, 1); // 设置索引为0的元素值为1
        atomicLongArray.set(2, 10); // 设置索引为2的元素值为10

        // 使用getAndUpdate方法更新索引为0的元素值，并且更新过程中打印原始值和新值
        long oldValue = atomicLongArray.getAndUpdate(0, currentValue -> currentValue + 1L); // 获取并增加索引为0的元素值
        System.out.println("旧值: " + oldValue); // 输出旧值，即更新前的值
        System.out.println("新值: " + atomicLongArray.get(0)); // 输出新值，即更新后的值通过直接获取验证

        // 使用Lambda表达式更新索引为2的元素值，将其乘以2并返回旧值和新值
        oldValue = atomicLongArray.getAndUpdate(2, currentValue -> currentValue * 2); // 获取并乘以2索引为2的元素值
        System.out.println("旧值: " + oldValue); // 输出旧值，即更新前的值（原来的值）
        System.out.println("新值: " + atomicLongArray.get(2)); // 输出新值，即更新后的值通过直接获取验证

    }
}

    // public static void test(@Range(from = 0, to = Integer.MAX_VALUE) int corePoolSize) {
    //
    //
    // }

    /*

     */
