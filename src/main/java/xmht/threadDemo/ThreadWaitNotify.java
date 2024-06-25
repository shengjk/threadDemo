package xmht.threadDemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 6/17/2024
 */
public class ThreadWaitNotify {
    static  boolean flag = true;
    static Object  lock = new Object();


    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();

    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread()+" flag isi true. wa@ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        System.out.println("wait=========== 前=============");
                        lock.wait();
                        System.out.println("wait=========== 后=============");
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println(Thread.currentThread()+" flag is false. wa@ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread()+" hold lock. nofity@ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag=false;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            synchronized (lock){
                System.out.println(Thread.currentThread()+" hold lock again. nofity@ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
