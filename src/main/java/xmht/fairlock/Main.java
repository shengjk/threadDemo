package xmht.fairlock;

/**
 * Created by shengjk1 on 2016/12/19.
 */

/**
 * thread2: PrintQueue
 thread3: PrintQueue
 thread4: PrintQueue
 thread5: PrintQueue
 thread0: 2PrintQueue
 the documen has been print thread0
 thread6: PrintQueue
 thread7: PrintQueue
 thread8: PrintQueue
 thread9: PrintQueue
 thread1: 2PrintQueue
 */
public class Main {
	public static void main(String[] args) {
		Thread thread[]=new Thread[10];
		PrintQueue printQueue=new PrintQueue();
		for (int i = 0; i < 10; i++) {
			thread[i]=new Thread(new Job(printQueue),"thread"+i);
		}
		
		for (int i = 0; i < 10; i++) {
			thread[i].start();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
