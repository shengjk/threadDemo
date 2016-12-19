package xmht.cinema;

/**
 * Created by shengjk1 on 2016/12/19.
 *
 * 在类中有两个非依赖属性，它们被多个线程共享，你必须同步每一个变量的访问，
 * 但是同一个时刻只允许一个线程访问一个属性变量，其他的某个线程访问另外一个属性变量
 *
 */
public class Main {
	public static void main(String[] args) {
		Cinema cinema=new Cinema();
		
		TicketOfficel ticketOfficel=new TicketOfficel(cinema);
		Thread thread1=new Thread(ticketOfficel,"ticketOfficel");
		
		TicketOfficel2 ticketOffice2=new TicketOfficel2(cinema);
		Thread thread2=new Thread(ticketOffice2,"ticketOffice2");
		
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("1111111 "+cinema.getVacanciesCinema1());
		System.out.println("2222222 "+cinema.getVacanciesCinema2());
		
		
		
	}
}
