package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

public class WaitNotifyExample {
    public static void main(String[] args) {
        final Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1: Waiting...");
                try {
                    lock.wait(); // 等待被唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Resumed.");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2: Performing some operation.");
                // 唤醒等待的线程
                lock.notify();
            }
        });

        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }
}