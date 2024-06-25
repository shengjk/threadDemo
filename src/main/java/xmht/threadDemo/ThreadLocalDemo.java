package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 6/18/2024
 */

/*
ThreadLocal，即线程变量，是一个以ThreadLocal对象为键、任意对象为值的存储结构。这
个结构被附带在线程上，也就是说一个线程可以根据一个ThreadLocal对象查询到绑定在这个
线程上的一个值。
可以通过set(T)方法来设置一个值，在当前线程下再通过get()方法获取到原先设置的值。


是的，我知道 `ThreadLocal`。

`ThreadLocal` 是 Java 中的一个类，它提供了一种方式来实现线程局部变量（Thread-Local Variables）。在 Java 多线程编程中，多个线程共享同一个对象的实例变量，这可能会导致线程安全问题。为了解决这个问题，可以使用 `ThreadLocal` 来为每个线程提供一个独立的变量副本。每个线程只能访问其自身线程的副本，无法访问其他线程的副本。这样可以确保线程之间的数据独立性，避免线程安全问题。

简单来说，`ThreadLocal` 提供了一种在多线程环境中为每个线程维护其自己的值的方式。它的使用场景包括数据库连接、事务管理等需要为每个线程维护独立状态的情况。在 Spring 框架中，常见的应用是使用 `ThreadLocal` 管理事务的生命周期等场景。当在全局变量的同时又想使用多例模式时，也可以使用 `ThreadLocal` 来实现。
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };
    private static final ThreadLocal<Long> TIME_THREADLOCAL1 = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
        TIME_THREADLOCAL1.set(System.currentTimeMillis());
    }

    public static final long end() {
        return (System.currentTimeMillis() - TIME_THREADLOCAL.get())+ (System.currentTimeMillis() - TIME_THREADLOCAL1.get());
    }

    public static void main(String[] args) {
        ThreadLocalDemo.begin();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Cost: "+ThreadLocalDemo.end()+" mills");
    }
}

