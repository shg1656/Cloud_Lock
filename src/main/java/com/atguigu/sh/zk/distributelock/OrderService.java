package com.atguigu.sh.zk.distributelock;

import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class OrderService {

	OrderNumGenerateUtil orderNumGenerateUtil=new OrderNumGenerateUtil();
	private Lock lock=new ReentrantLock();
	private ZKlock zklock=new zkDistributedLock();
	
	public void getOrderNumber() {
		zklock.lock();
		try {
			System.out.println("订单编号"+orderNumGenerateUtil.getOrderNumber());
		} finally {
			zklock.unlock();
		}
		
	}
		
	public static void main(String[] args) {
	
		for(int i=1;i<=200;i++) {
			new Thread(()->{
				new OrderService().getOrderNumber();
				
			},String.valueOf(i)).start();
		}
	}
}
