package com.atguigu.sh.zk.distributelock;

import java.nio.channels.NetworkChannel;
import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZKAbstractTemplateLock implements ZKlock {
	//与zookeeper建立连接
	public static final String ZKSERVER="192.168.94.128:2181";
	public static final int TIME_OUT=45*1000;
	protected String path="/myzkLock";
	protected CountDownLatch countDownLatch=null;
	
	ZkClient zkClient =new ZkClient(ZKSERVER, TIME_OUT);
	@Override
	public void lock() {
		if(tryzKlock()) {
			System.out.println(Thread.currentThread().getName()+"\t 抢占锁成功");
		}else {
			waitZkLock();
			
			lock();
		}
	}
	public abstract void waitZkLock();
	public abstract boolean tryzKlock();
	//模板设计模式，固定化的流程升级到父类定死规范，但是，具体落地实现方法下给予子类各自实现
	

	@Override
	public void unlock() {
		if(zkClient!=null) {
			zkClient.close();//等价于在zk服务器上执行quit推出命令
		}
		System.out.println(Thread.currentThread().getName()+"\t 释放锁成功");
		System.out.println();
		System.out.println();
		
	}

}
