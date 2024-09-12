package xmht.threadDemo;

class SumThread extends Thread {
    private int start;
    private int end;
    private long sum;

    public SumThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
    }

    public long getSum() {
        return sum;
    }
}

public class MultiThreadedSum {
    public static void main(String[] args) {
        int totalThreads = 4;
        int range = 1000000;
        SumThread[] threads = new SumThread[totalThreads];
        int part = range / totalThreads;

        // 4个线程分别计算
        for (int i = 0; i < totalThreads; i++) {
            int start = i * part + 1;
            int end = (i == totalThreads - 1) ? range : (i + 1) * part;
            threads[i] = new SumThread(start, end);
            threads[i].start();
        }

        long totalSum = 0;

        // 等待所有线程完成
        try {
            for (SumThread thread : threads) {
                thread.join();
                totalSum += thread.getSum(); // 累加每个线程的结果
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 输出最终结果
        System.out.println("从 1 加到 1,000,000 的和是: " + totalSum);
    }
}