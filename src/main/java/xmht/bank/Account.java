package xmht.bank;

/**
 * Created by shengjk1 on 2016/12/19.
 */
public class Account {
	private double balance;
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public synchronized void addAmount(double amount){
		double tmp=balance;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp+=amount;
		balance=tmp;
	}
	
	
	public  void subtractAmouont(double amount){
		synchronized(this){
			double tmp=balance;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tmp-=amount;
			balance=tmp;
		}
	}
}
