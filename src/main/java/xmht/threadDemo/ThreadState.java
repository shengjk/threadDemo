package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 6/14/2024
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();

        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();


    }

    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        //wait(10) 表示线程等待指定的时间后自动唤醒，而 wait(0) 表示线程立即进入等待状态，并在被唤醒前一直等待，但不等待任何时间
                        /*
                        在Java中，`wait()` 方法会使当前线程进入等待状态，并且会释放对象的锁。当一个线程调用 `wait()` 方法时，它会暂停执行，释放对象锁，并等待其他线程调用相同对象上的 `notify()` 或 `notifyAll()` 方法来唤醒它。

                        要注意的是，`wait()` 方法会导致当前线程阻塞，但不会阻塞整个程序的执行。其他线程仍然可以继续执行，只有调用 `wait()` 方法的线程会进入等待状态。一旦该线程被唤醒（通过 `notify()` 或 `notifyAll()`）并重新获得锁时，它会继续执行。

                        因此，`wait()` 方法会阻塞调用它的线程，但并不会导致整个程序的停止。其他线程可以继续运行，直到满足条件使调用 `wait()` 方法的线程被唤醒。
                         */
                        System.out.println("======wait 前===============");
                        Waiting.class.wait();
                        System.out.println("======wait 后===============");
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
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
