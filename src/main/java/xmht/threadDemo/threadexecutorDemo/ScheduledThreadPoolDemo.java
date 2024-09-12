package xmht.threadDemo.threadexecutorDemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 2024/9/6
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        //延迟执行
        scheduledExecutorService.schedule(() -> {
            System.out.println("Task executed after 5 seconds");
        }, 5, TimeUnit.SECONDS);

        //周期性定期执行
        //任务的开始时间是固定的，即每隔指定的时间间隔开始执行，忽略任务执行所需的时间。如果任务执行时间超过间隔时间，下一次的开始时间会立即执行。
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // try {
            //     TimeUnit.SECONDS.sleep(1);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            System.out.println("Task executed every 3 seconds "+new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
        }, 0, 4, TimeUnit.SECONDS);

        // 周期性固定延迟执行
        //任务之间的延迟是基于上一个任务的执行结束时间。如果任务执行时间超过延迟，则下一次的开始时间会相应推迟。
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Task executed after 2 seconds delay "+new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
        }, 0, 2, TimeUnit.SECONDS);



        // scheduledExecutorService.shutdown();
    }
}
