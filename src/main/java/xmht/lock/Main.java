package xmht.lock;

/**
 * Created by shengjk1 on 2016/12/19.
 */


/**
 * thread0: PrintQueue
 thread0: 2PrintQueue
 the documen has been print thread0
 thread1: PrintQueue
 thread1: 2PrintQueue
 the documen has been print thread1
 thread2: PrintQueue
 thread2: 2PrintQueue
 the documen has been print thread2
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
		}
	}
	
}
