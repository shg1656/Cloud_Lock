package com.atguigu.sh.zk.distributelock;

import java.util.HashSet;
import java.util.Set;

public class equals {

	public static void main(String[] args) {
		String s1 = new String("a");
		String s2 = new String("a");
		System.out.println(s1.equals(s2));
		Set<Object> h1 = new HashSet<>();
		h1.add(s1);
		h1.add(s2);
		System.out.println(s1.hashCode()+"--"+s2.hashCode());
		System.out.println(h1.size());
		System.out.println(h1);
		
		
		Person p1 = new Person(1);
		Person p2 = new Person(11);
		System.out.println(p1.equals(p2));
		Set<Object> h2 = new HashSet<>();
		h2.add(p1);
		h2.add(p2);
		System.out.println(p1.hashCode()+"--"+p2.hashCode());
		System.out.println(h2.size());
		System.out.println(h2);
		int[] intArray0 =new int[0];
		System.out.println(intArray0.length);
	}
}
