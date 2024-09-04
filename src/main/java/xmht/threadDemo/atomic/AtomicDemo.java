package xmht.threadDemo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shengjk1
 * @date 6/25/2024
 */
public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.compareAndSet(1,2);
        System.out.println(atomicInteger.get());

    }
}
