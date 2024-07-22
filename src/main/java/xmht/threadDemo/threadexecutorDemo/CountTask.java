package xmht.threadDemo.threadexecutorDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author shengjk1
 * @date 7/3/2024
 */
/*

工作窃取（work-stealing）算法是指某个线程从其他队列里窃取任务来执行。那么，为什么
需要使用工作窃取算法呢？ 假如我们需要做一个比较大的任务，可以把这个任务分割为若干
互不依赖的子任务，为了减少线程间的竞争，把这些子任务分别放到不同的队列里，并为每个
队列创建一个单独的线程来执行队列里的任务，线程和队列一一对应。比如A线程负责处理A
队列里的任务。但是，有的线程会先把自己队列里的任务干完，而其他线程对应的队列里还有
任务等待处理。干完活的线程与其等着，不如去帮其他线程干活，于是它就去其他线程的队列
里窃取一个任务来执行。而在这时它们会访问同一个队列，所以为了减少窃取任务线程和被
窃取任务线程之间的竞争，通常会使用双端队列，被窃取任务线程永远从双端队列的头部拿
任务执行，而窃取任务的线程永远从双端队列的尾部拿任务执行


工作窃取算法的优点：充分利用线程进行并行计算，减少了线程间的竞争。
工作窃取算法的缺点：在某些情况下还是存在竞争，比如双端队列里只有一个任务时。并
且该算法会消耗了更多的系统资源，比如创建多个线程和多个双端队列


步骤1 分割任务。首先我们需要有一个fork类来把大任务分割成子任务，有可能子任务还
是很大，所以还需要不停地分割，直到分割出的子任务足够小。
步骤2 执行任务并合并结果。分割的子任务分别放在双端队列里，然后几个启动线程分
别从双端队列里获取任务执行。子任务执行完的结果都统一放在一个队列里，启动一个线程
从队列里拿数据，然后合并这些数据。
Fork/Join使用两个类来完成以上两件事情。
①ForkJoinTask：我们要使用ForkJoin框架，必须首先创建一个ForkJoin任务。它提供在任务
中执行fork()和join()操作的机制。通常情况下，我们不需要直接继承ForkJoinTask类，只需要继
承它的子类，Fork/Join框架提供了以下两个子类。
·RecursiveAction：用于没有返回结果的任务。
·RecursiveTask：用于有返回结果的任务。
②ForkJoinPool：ForkJoinTask需要通过ForkJoinPool来执行。
任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。当
一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任
务



通过这个例子，我们进一步了解ForkJoinTask，ForkJoinTask与一般任务的主要区别在于它
需要实现compute方法，在这个方法里，首先需要判断任务是否足够小，如果足够小就直接执
行任务。如果不足够小，就必须分割成两个子任务，每个子任务在调用fork方法时，又会进入
compute方法，看看当前子任务是否需要继续分割成子任务，如果不需要继续分割，则执行当
前子任务并返回结果。使用join方法会等待子任务执行完并得到其结果。

 */
public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD=2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum=0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute){
            for (int i = start; i <=end ; i++) {
                sum+=i;
            }
        }else{
            //分裂成两个任务
            int mid=(start+end)/2;
            CountTask leftTask = new CountTask(start, mid);
            CountTask rightTask = new CountTask(mid, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            System.out.println("leftTask.isCompletedNormally() = " + leftTask.isCompletedNormally());
            System.out.println("leftTask.getException() = " + leftTask.getException());
            //等待子任务完成
             int leftResult=leftTask.join();
             int rightResult=rightTask.join();
             sum=leftResult+rightResult;
        }

        return sum;
    }

    public static void main(String[] args) {
        String pp = System.getProperty
                ("java.util.concurrent.ForkJoinPool.common.parallelism");
        System.out.println("pp = " + pp);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("forkJoinPool.getCommonPoolParallelism() = " + forkJoinPool.getCommonPoolParallelism());

        CountTask task = new CountTask(1, 4);
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
    }
}
