package xmht;

import xmht.bank.Account;
import xmht.bank.Bank;
import xmht.bank.Company;

/**
 * Created by shengjk1 on 2016/12/19.
 */
public class Main {
	public static void main(String[] args) {
		/*Thread task=new PrimeGenerator();
		task.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("================");
		System.out.println(task.isAlive());
		System.out.println(task.isInterrupted());
		task.interrupt();
		System.out.println("================");
		System.out.println(task.isAlive());
		System.out.println(task.isInterrupted());*/
		
//		FileSearch searcher=new FileSearch("E:\\","index.html");
//		Thread thread=new Thread(searcher);
//		thread.start();
//
//		try {
//			TimeUnit.SECONDS.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		thread.interrupt();
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
