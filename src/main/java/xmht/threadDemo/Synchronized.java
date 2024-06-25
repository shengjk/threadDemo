package xmht.threadDemo;

/**
 * @author shengjk1
 * @date 6/17/2024
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class){}
        m();
    }

    public static synchronized void m(){}
}
