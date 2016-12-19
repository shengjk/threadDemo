package xmht.bank;

/**
 * Created by shengjk1 on 2016/12/19.
 */
public class Main {
	public static void main(String[] args) {
		Account account=new Account();
		account.setBalance(1000);
		
		Company company=new Company(account);
		Thread companyThread=new Thread(company);
		
		Bank bank=new Bank(account);
		Thread bankThread=new Thread(bank);
		
		System.out.printf("Account : Initial Balance: %f \n ",account.getBalance());
		
		companyThread.start();
		bankThread.start();
		
		
		try {
			companyThread.join();
			bankThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
}
