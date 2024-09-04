package xmht.threadDemo;

/**
 * @author shengjk1
 * @date 2024/9/3
 */
public class SynchronizedDemo {
    public void test() throws InterruptedException {
        synchronized (this){
            System.out.println("test");
            this.wait(10);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new SynchronizedDemo().test();
        System.out.println("SynchronizedDemo.test 已返回 ");

        new SynchronizedDemo().notify();
    }
}
