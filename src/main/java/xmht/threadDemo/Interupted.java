package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 2024/8/15
 */
public class Interupted {
    public static void main(String[] args) throws Exception {
// sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
// busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
// 休眠5秒，让sleepThread和busyThread充分运行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
// 防止sleepThread和busyThread立刻退出SleepUtils.second(2);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Thread countThread = new Thread(new Runner(), "CountThread");
        countThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countThread.interrupt();

        Runner runner = new Runner();
        Thread countThread2 = new Thread(runner, "CountThread2");
        countThread2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runner.cancel();


    }
    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println("111 "+Thread.interrupted());
            }
        }
    }
    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }


    private static class Runner implements Runnable{
        private long i;
        private volatile boolean started = true;

        @Override
        public void run() {
            while (started && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i="+i);
        }
        public void cancel() {
            started=false;
        }
    }
}
