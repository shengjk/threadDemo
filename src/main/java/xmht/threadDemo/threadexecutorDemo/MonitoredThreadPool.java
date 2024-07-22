package xmht.threadDemo.threadexecutorDemo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class MonitoredThreadPool extends ThreadPoolExecutor {

    private final AtomicLong totalTime = new AtomicLong(0);
    private final AtomicLong taskCount = new AtomicLong(0);
    private final AtomicLong maxTime = new AtomicLong(0);
    private final AtomicLong minTime = new AtomicLong(Long.MAX_VALUE);

    public MonitoredThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                               BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long startTime = ((MonitoredRunnable) r).getStartTime();
            long endTime = System.nanoTime();
            long executeTime = endTime - startTime;
            totalTime.addAndGet(executeTime);

            taskCount.incrementAndGet();

            updateMaxMinTime(executeTime);

            // Other monitoring tasks can be added here

        } finally {
            super.afterExecute(r, t);
        }
    }

    /*
    在 `updateMaxMinTime` 方法中使用 `while(true)` 循环是为了确保最终成功更新最大执行时间和最小执行时间的值，避免由于并发操作导致数据不一致或错误更新的情况发生。
在多线程环境下，多个线程可能同时竞争更新 `maxTime` 和 `minTime` 的值，如果不使用循环来确保更新操作的原子性，就有可能出现数据竞争的情况。使用 `while(true)` 循环加上 CAS(compare-and-swap) 操作可以确保在竞争时正确更新数据，直到成功为止。
具体来说，循环的作用是不断尝试比较当前值和新值，并使用 CAS 操作更新，直到成功为止。如果更新失败，则表示当前值已经被其他线程修改过，此时需要重新读取当前值并再次尝试更新，直至更新成功。
这种写法保证了在高并发情况下的数据一致性，避免由于并发操作导致的数据错误。
     */
    private void updateMaxMinTime(long executeTime) {
        while (true) {
            long currentMax = maxTime.get();
            if (executeTime > currentMax) {
                if (maxTime.compareAndSet(currentMax, executeTime)) {
                    break;
                }
            } else {
                break;
            }
        }

        while (true) {
            long currentMin = minTime.get();
            if (executeTime < currentMin) {
                if (minTime.compareAndSet(currentMin, executeTime)) {
                    break;
                }
            } else {
                break;
            }
        }
    }

    @Override
    protected void terminated() {
        System.out.println("Average Task Execution Time: " + (totalTime.get() / taskCount.get()) + " nanoseconds");
        System.out.println("Max Task Execution Time: " + maxTime.get() + " nanoseconds");
        System.out.println("Min Task Execution Time: " + minTime.get() + " nanoseconds");
        super.terminated();
    }

    // Custom MonitoredRunnable task for tracking start time
    private static class MonitoredRunnable implements Runnable {
        private final Runnable task;
        private final long startTime;

        public MonitoredRunnable(Runnable task) {
            this.task = task;
            this.startTime = System.nanoTime();
        }

        public long getStartTime() {
            return startTime;
        }

        @Override
        public void run() {
            task.run();
        }
    }

    @Override
    public void execute(Runnable command) {
        super.execute(new MonitoredRunnable(command));
    }

    public static void main(String[] args) {
        MonitoredThreadPool threadPool = new MonitoredThreadPool(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        threadPool.shutdown();
    }
}