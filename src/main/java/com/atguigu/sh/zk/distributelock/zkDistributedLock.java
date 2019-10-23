package com.atguigu.sh.zk.distributelock;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;

public class zkDistributedLock extends ZKAbstractTemplateLock{

	
	
	@Override
	public boolean tryzKlock() {
		try {
			zkClient.createEphemeral(path);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	@Override
	public void waitZkLock() {
		
		IZkDataListener iZkDataListener = new  IZkDataListener(){

			@Override
			public void handleDataChange(String arg0, Object arg1) throws Exception {
				
			}

			@Override
			public void handleDataDeleted(String arg0) throws Exception {
				//如果倒计时器被加载  
				if(countDownLatch!=null) {
					//减去一个
					countDownLatch.countDown();
				}
			}
			
		};
		zkClient.subscribeDataChanges(path, iZkDataListener);
		if(zkClient.exists(path)){
			//不能干什么事情，只能等path也即临时节点被删除后才能继续向下运行
			
			//？？？怎么让程序卡在这里
			//使用倒计时器countDownLatch  必须有一个线程执行完毕才会进行操作，否则等待
			countDownLatch=	new CountDownLatch(1);
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//释放监听器
		zkClient.unsubscribeDataChanges(path, iZkDataListener);
		
	}



}
