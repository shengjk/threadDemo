package xmht.threadDemo;

import java.util.LinkedList;
import java.util.Queue;

class ProductConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 5;
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (queue.size() == LIMIT) {
                    System.out.println("队列满，生产者等待...");
                    lock.wait(); // 等待直到有空位
                }
                queue.add(value);
                System.out.println("生产者: 生产 " + value);
                value++;
                lock.notify(); // 唤醒消费者
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (queue.isEmpty()) {
                    System.out.println("队列空，消费者等待...");
                    lock.wait(); // 等待直到有可消费的商品
                }
                int value = queue.poll();
                System.out.println("消费者: 消费 " + value);
                lock.notify(); // 唤醒生产者
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ProductConsumer productConsumer = new ProductConsumer();
        new Thread(() -> {
            try { productConsumer.consume(); } catch (InterruptedException e) {}
        }).start();
        new Thread(() -> {
            try { productConsumer.produce(); } catch (InterruptedException e) {}
        }).start();
    }
}