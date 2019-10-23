package com.atguigu.sh.zk.distributelock;

public interface ZKlock {

	public void lock();
	public void unlock();
}
