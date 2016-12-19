package xmht.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shengjk1 on 2016/12/19.
 *
 *
 *
 *
 *
 * 1、ReentrantLock 拥有Synchronized相同的并发性和内存语义，此外还多了 锁投票，定时锁等候和中断锁等候
 线程A和B都要获取对象O的锁定，假设A获取了对象O锁，B将等待A释放对O的锁定，
 如果使用 synchronized ，如果A不释放，B将一直等下去，不能被中断
 如果 使用ReentrantLock，如果A不释放，可以使B在等待了足够长的时间以后，中断等待，而干别的事情
 
 ReentrantLock获取锁定与三种方式：
 a)  lock(), 如果获取了锁立即返回，如果别的线程持有锁，当前线程则一直处于休眠状态，直到获取锁
 b) tryLock(), 如果获取了锁立即返回true，如果别的线程正持有锁，立即返回false；
 c)tryLock(long timeout,TimeUnit unit)，   如果获取了锁定立即返回true，如果别的线程正持有锁，会等待参数给定的时间，在等待的过程中，如果获取了锁定，就返回true，如果等待超时，返回false；
 d) lockInterruptibly:如果获取了锁定立即返回，如果没有获取锁定，当前线程处于休眠状态，直到或者锁定，或者当前线程被别的线程中断
 
 2、synchronized是在JVM层面上实现的，不但可以通过一些监控工具监控synchronized的锁定，而且在代码执行时出现异常，JVM会自动释放锁定，但是使用Lock则不行，lock是通过代码实现的，要保证锁定一定会被释放，就必须将unLock()放到finally{}中
 
 3、在资源竞争不是很激烈的情况下，Synchronized的性能要优于ReetrantLock，但是在资源竞争很激烈的情况下，Synchronized的性能会下降几十倍，但是ReetrantLock的性能能维持常态；
 *
 *
 */
public class PrintQueue {
	private final Lock queueLock=new ReentrantLock();
	
	public void printJob(Object document){
		queueLock.lock();
		
		try {
			Long duration=(long)(Math.random()*10000);
			System.out.println(Thread.currentThread().getName()
			+": PrintQueue ");
			Thread.sleep(duration);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
	}
}
