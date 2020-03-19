package com.company.datarace.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentObjects {

	public static ArrayList<com.company.datarace.objects.ListObject> arraylist1;
	public static ArrayList<com.company.datarace.objects.ListObject> tsarraylist;
	public static HashMap hashMap1;

	private static final Lock lock = new ReentrantLock();

	public static void initObjects() {

		arraylist1 = new ArrayList<com.company.datarace.objects.ListObject>();
		tsarraylist = new ArrayList<com.company.datarace.objects.ListObject>();
		hashMap1 = new HashMap();

	}

	public static void addToTSArrayList(ListObject obj){
		lock.lock();
		try{
			tsarraylist.add(obj);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
