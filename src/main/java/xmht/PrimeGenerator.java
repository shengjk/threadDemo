package xmht;


/**
 * Created by shengjk1 on 2016/12/19.
 */
public class PrimeGenerator extends Thread {
	@Override
	public void run() {
		long number=1;
		while (true){
			if (isPrime(number)){
				System.out.printf(" Number %d is Prime",number);
				System.out.println();
			}
			
			if (isInterrupted()) {
				System.out.printf("The Prime Generator has been Interrupted");
				System.out.println();
				return;
			}
			number++;
		}
	}
	
	private boolean isPrime(long number) {
		if (number <=2) {
			return true;
		}
		
		for (long i=2; i<number; i++){
			if ((number % i)==0) {
				return false;
			}
		}
		return true;
	}
}
