package xmht.threadDemo;

public class Counter {
     private int count = 0; // 共享资源：计数器

    // synchronized 块用于保护对 count 的并发访问
    public synchronized void increment() {
            count++; // 对共享资源的操作（这里是增加计数器的值）
    }

    public int getCount() {
        return count; // 外部访问共享资源（可能需要同步）
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(); // 创建 Counter 实例对象
        // 创建多个线程，模拟并发增加计数器的值
        for (int i = 0; i < 10; i++) { // 创建 10 个线程来并发增加计数器的值
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) { // 每个线程都尝试增加计数器值多次以模拟并发情况
                    counter.increment(); // 使用 synchronized 块来安全地增加计数器值
                }
            });
            thread.start(); // 启动线程
            thread.join();
        } // 循环结束后，所有线程都已经启动并尝试增加计数器的值。由于使用了 synchronized 块，计数器的值将准确累积而不会发生冲突或重叠增加的情况。可以检查 getCount() 的返回值来验证这一点。
        System.out.println(counter.count);
    }
}
