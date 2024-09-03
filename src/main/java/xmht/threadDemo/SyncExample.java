package xmht.threadDemo;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class SyncExample {
    // 定义日期格式
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 格式化日期
    private String name;

    public SyncExample(String name) {
        this.name = name;
    }

    // 实例方法
    public synchronized void instanceMethod() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sdf.format(System.currentTimeMillis())+" "+this.name+" 实例方法被调用");
    }

    // 静态方法
    public static synchronized void staticMethod() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sdf.format(System.currentTimeMillis())+" 静态方法被调用");
    }

    public static void main(String[] args) {
        SyncExample obj1 = new SyncExample("obj1");
        SyncExample obj2 = new SyncExample("obj2");

        // 实例方法测试
        new Thread(() -> obj1.instanceMethod()).start();
        new Thread(() -> obj1.instanceMethod()).start();//可重入
        new Thread(() -> obj1.instanceMethod()).start();
        new Thread(() -> obj1.instanceMethod()).start();
        new Thread(() -> obj2.instanceMethod()).start(); // 可以并发执行

        // 静态方法测试
        new Thread(SyncExample::staticMethod).start();
        new Thread(SyncExample::staticMethod).start(); // 会互斥执行
    }
}