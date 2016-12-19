package xmht.proandcounsumer;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shengjk1 on 2016/12/19.
 */
//数据存储类
public class EventStorage {
	private int maxSize;
	private List<Date> stroage;
	
	public EventStorage(int maxSize, List<Date> stroage) {
		this.maxSize = maxSize;
		this.stroage = stroage;
	}
	
	public synchronized void set(){
		while (stroage.size()==maxSize){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stroage.add(new Date());
			System.out.println(" === "+stroage.size());
			notifyAll();
		}
	}
	
	public synchronized void get(){
		while (stroage.size()==0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stroage.add(new Date());
			System.out.println(" ===get "+stroage.size()+" "+((LinkedList<?>)stroage).poll());
			notifyAll();
		}
	}
	
}
