package xmht.threadDemo.lockDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shengjk1
 * @date 6/25/2024
 */
public class LockUseCase {
    /*
    虽然它缺少了（通过synchronized块或者方法所提
供的）隐式获取释放锁的便捷性，但是却拥有了锁获取与释放的可操作性、可中断的获取锁以
及超时获取锁等多种synchronized关键字所不具备的同步特性。
     */
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {

        }finally {
            lock.unlock();
        }
    }
}
