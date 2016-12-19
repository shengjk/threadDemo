package xmht.lock;

/**
 * Created by shengjk1 on 2016/12/19.
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
