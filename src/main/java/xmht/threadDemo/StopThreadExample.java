package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

public class StopThreadExample {

    // 使用volatile修饰的变量来确保所有线程都能看到最新的标志位值
    private volatile boolean isRunning = true;

    // 一个任务执行线程，它持续检查标志位并根据标志位决定是否继续运行
    public void runTask() {
        while (isRunning) { // 使用volatile修饰的变量确保我们能正确读取到最新的状态
            // 这里模拟一些耗时任务
            System.out.println("执行任务...");
            try {
                Thread.sleep(1000); // 模拟耗时操作，这里暂停一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("任务执行线程已经停止.");
    }

    public static void main(String[] args) throws InterruptedException {
        StopThreadExample example = new StopThreadExample();
        Thread taskThread = new Thread(() -> example.runTask()); // 启动任务执行线程
        taskThread.start(); // 开始执行任务线程
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ; // 让主线程暂停几秒以便观察效果，模拟主线程正在做其他事情的情况
        System.out.println("停止任务执行线程..."); // 在主线程中通知任务执行线程停止执行
        example.isRunning = false; // 设置标志位来停止任务执行线程，所有线程都会看到最新的标志位值，因为使用了volatile关键字修饰isRunning变量
        taskThread.join(); // 确保任务执行线程停止后主线程继续执行接下来的代码或结束程序（这一步依赖于主线程的下一步动作）
        System.out.println("主线程结束."); // 当任务执行线程已经停止后，主线程结束程序或继续其他操作（这里模拟主线程的下一步动作）
    }
}