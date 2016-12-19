package xmht.proandcounsumer;

/**
 * Created by shengjk1 on 2016/12/19.
 */
public class Producer implements Runnable {
	private EventStorage  eventStorage;
	
	public Producer(EventStorage eventStorage) {
		this.eventStorage = eventStorage;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			eventStorage.set();
		}
	}
}
