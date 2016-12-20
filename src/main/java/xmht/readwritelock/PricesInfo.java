package xmht.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by shengjk1 on 2016/12/20.
 *
 * 读写锁
 * 读锁操作是允许多个线程同时访问，但是写操作时只允许一个线程进行
 * 当获取lock的读锁时，不可进行修改操作，否则将引起数据不一致的错误
 */
public class PricesInfo {
	private double price1;
	private double price2;
	
	private ReadWriteLock lock;
	
	public PricesInfo() {
		price1=1;
		price2=2;
		lock=new ReentrantReadWriteLock();
	}
	
	public double getPrice1(){
		lock.readLock().lock();
		double value=price1;
		lock.readLock().unlock();
		return value;
	}
	
	public double getPrice2(){
		lock.readLock().lock();
		double value=price2;
		lock.readLock().unlock();
		return value;
	}
	
	
	public void setPrices(double price1,double price2){
		lock.writeLock().lock();
		this.price1=price1;
		this.price2=price2;
		lock.writeLock().unlock();
		
	}
}
