package xmht.threadDemo.ExchangerDemo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shengjk1
 * @date 6/25/2024
 */
/*
Exchanger（交换者）是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交
换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。这两个线程通过
exchange方法交换数据，如果第一个线程先执行exchange()方法，它会一直等待第二个线程也
执行exchange方法，当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产
出来的数据传递给对方
 */
public class ExchangerDemo {
   private static final Exchanger       exgr          = new Exchanger<String>();
   // 实现是无限队列，建议使用有界队列。
   private static  ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);



    public static void main(String[] args) {
        System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());
        //execute()方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功。
        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String a="银行流水A";
                    exgr.exchange(a);
                } catch (InterruptedException e) {
                }
            }
        });

        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String b="银行流水B";
                    String a=(String) exgr.exchange(b);
                    System.out.println("a="+a +" b="+b);

                } catch (InterruptedException e) {
                }
            }
        });
        /*
        可以通过调用线程池的shutdown或shutdownNow方法来关闭线程池。它们的原理是遍历线
程池中的工作线程，然后逐个调用线程的interrupt方法来中断线程，所以无法响应中断的任务
可能永远无法终止。但是它们存在一定的区别，shutdownNow首先将线程池的状态设置成
STOP，然后尝试停止所有的正在执行或暂停任务的线程，并返回等待执行任务的列表，而
shutdown只是将线程池的状态设置成SHUTDOWN状态，然后中断所有没有正在执行任务的线
程
         */
        newFixedThreadPool.shutdown();
    }

}
