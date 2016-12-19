package xmht.cinema;

/**
 * Created by shengjk1 on 2016/12/19.
 *
 * 售票类
 */
public class TicketOfficel implements Runnable{
	private Cinema cinema;
	
	public TicketOfficel(Cinema cinema) {
		this.cinema = cinema;
	}
	
	@Override
	public void run() {
		cinema.sellTickets1(3);
		cinema.sellTickets1(2);
		cinema.sellTickets2(2);
		cinema.returnTickets1(3);
		cinema.sellTickets1(5);
		cinema.sellTickets2(2);
		cinema.sellTickets2(2);
		cinema.sellTickets2(2);
	}
}
