package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

public class WaitWithTimeoutExample {
    private final Object lock = new Object();
    private boolean conditionMet = false;

    public void awaitConditionWithTimeout(long timeout) throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " has acquired the lock");

            lock.wait(timeout);

            // try {
            //     TimeUnit.SECONDS.sleep(10);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }

            System.out.println(Thread.currentThread().getName() + " has woken up");
        }
    }

    public void setConditionMet(boolean value) {
        synchronized (lock) {
            System.out.println("setConditionMet====");
        }
    }

    public static void main(String[] args) {
        WaitWithTimeoutExample example = new WaitWithTimeoutExample();

        new Thread(() -> {
            try {
                example.awaitConditionWithTimeout(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            example.setConditionMet(true);
        }).start();
    }
}