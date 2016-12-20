package xmht.readwritelock;

/**
 * Created by shengjk1 on 2016/12/20.
 */
public class Writer implements Runnable {
	private PricesInfo pricesInfo;
	
	public Writer(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.printf("writer:attempt to modify the prices.\n");
			pricesInfo.setPrices(Math.random()*10,Math.random()*8);
			System.out.printf("writer:have been modified the prices.\n");
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
