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
	
	public EventStorage() {
		maxSize=10;
		stroage=new LinkedList<>();
	}
	
	
	public synchronized void set(){
		while (stroage.size()==maxSize){
			try {
				System.out.println("set wait");
				wait();//必须在循环里面
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stroage.add(new Date());
		System.out.println(" ===set "+stroage.size());
		notifyAll();
	}
	
	public synchronized void get(){
		while (stroage.size()==0){
			try {
				System.out.println("get wait");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		stroage.add(new Date());
		System.out.println(" ===get "+stroage.size()+" "+((LinkedList<?>)stroage).poll());
		notifyAll();
	}
	
}
