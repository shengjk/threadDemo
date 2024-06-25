package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 6/14/2024
 */
public class ThreadState1 {
    static ThreadState1 threadState1=new ThreadState1();
    public static void main(String[] args) {
        new Thread(new Waiting(),"WaitingThread-1").start();
        new Thread(new Waiting(),"WaitingThread-2").start();

        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();


    }


    static class Waiting implements Runnable{
        @Override
        public void run() {
                synchronized (ThreadState1.class){
                    while (true){
                            try {
                                TimeUnit.SECONDS.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                    }
                }
        }
    }

    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (threadState1){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
