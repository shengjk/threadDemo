package xmht.threadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author shengjk1
 * @date 6/17/2024
 */
public class Synchronized {
    private String name="11";

    public synchronized void setName(String name){
        this.name=name;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return this.name;
    }

    public static void main(String[] args) {
        // synchronized (Synchronized.class){}
        // m();
        for (int i = 0; i < 10000; i++) {
            Synchronized student = new Synchronized();
            new Thread(()-> student.setName("aaa")).start();
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("========"+student.getName());
        }

    }
    // public static synchronized void m(){}

}
