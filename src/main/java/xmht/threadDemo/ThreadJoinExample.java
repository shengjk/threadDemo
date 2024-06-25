package xmht.threadDemo;

public class ThreadJoinExample {

    public static void main(String[] args) {
        // 定义数组和线程数量
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = 4;

        // 创建线程数组
        Thread[] threads = new Thread[numThreads];
        CalculateThread[] calculateThreads = new CalculateThread[numThreads];

        int chunkSize = array.length / numThreads;

        // 初始化并启动线程
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? array.length : start + chunkSize;
            calculateThreads[i] = new CalculateThread(array, start, end);
            threads[i] = new Thread(calculateThreads[i]);
            threads[i].start();
        }

        // 等待所有线程完成计算
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 汇总各个线程计算结果
        int totalSum = 0;
        for (CalculateThread calcThread : calculateThreads) {
            totalSum += calcThread.getPartialSum();
        }

        System.out.println("Total sum: " + totalSum);
    }
}

class CalculateThread implements Runnable {
    private int[] array;
    private int start;
    private int end;
    private int partialSum;

    public CalculateThread(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.partialSum = 0;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += array[i];
        }
    }

    public int getPartialSum() {
        return partialSum;
    }
}