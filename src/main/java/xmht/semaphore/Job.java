package xmht.semaphore;

/**
 * Created by shengjk1 on 2016/12/20.
 */
public class Job implements Runnable {
	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}
	
	@Override
	public void run() {
		System.out.printf("%s: Going to print  ajob \n",Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: the document has been printed  ajob \n",Thread.currentThread().getName());
	}
}
