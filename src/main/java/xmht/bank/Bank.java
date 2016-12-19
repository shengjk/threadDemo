package xmht.bank;

/**
 * Created by shengjk1 on 2016/12/19.
 */
public class Bank implements Runnable{
	private Account account;
	
	public Bank(Account account) {
		this.account = account;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.subtractAmouont(1000);
		}
	}
}
