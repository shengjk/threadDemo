package xmht.proandcounsumer;

/**
 * Created by shengjk1 on 2016/12/19.
 */
public class Main {
	public static void main(String[] args) {
		EventStorage eventStorage=new EventStorage();
		
		Producer producer=new Producer(eventStorage);
		Thread  thread1=new Thread(producer);
		
		Consumer consumer=new Consumer(eventStorage);
		Thread thread2=new Thread(consumer);
		
		thread1.start();
		thread2.start();
		
		
		try {
			System.out.println(thread2.isAlive());
			thread2.join();
			System.out.println(thread2.isAlive());
			System.out.println(thread2.getState().toString());
			thread1.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
