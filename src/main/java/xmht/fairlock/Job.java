package xmht.fairlock;

/**
 * Created by shengjk1 on 2016/12/19.
 */
public class Job implements Runnable {
	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}
	
	@Override
	public void run() {
		System.out.println(" going to print a document"+Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.println(" the documen has been print "+Thread.currentThread().getName());
	}
}
