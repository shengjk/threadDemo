package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 5/29/2024
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread();
        thread.start();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interruted!");
                        break;
                    }
                    Thread.yield();
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("thread1==");
                }
            }
        };

        thread1.start();
        Thread.sleep(2000);
        thread1.interrupt();

    }
}

