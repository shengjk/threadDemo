package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 6/18/2024
 */
public class ThreadWait {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        // o.wait(); //java.lang.IllegalMonitorStateException

        synchronized (o){
            System.out.println("==========");
            o.wait(10);
            System.out.println("=====####=====");
        }


        new Thread(){
            @Override
            public void run() {
                synchronized (o){
                    try {
                        System.out.println("我是線程");
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
