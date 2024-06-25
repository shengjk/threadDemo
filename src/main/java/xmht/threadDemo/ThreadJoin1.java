package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 6/24/2024
 */
public class ThreadJoin1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread("thread") {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();

        thread.join();
        System.out.println("threadB执行完成，主线程继续往下执行");
    }
}

