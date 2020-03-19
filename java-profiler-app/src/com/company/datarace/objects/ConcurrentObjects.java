package com.company.datarace.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConcurrentObjects.java
 *
 * Maintains the objects which are concurrently accessed in the application
 * (the shared arraylists and the HashMap)
 */

public class ConcurrentObjects {

	public static ArrayList<com.company.datarace.objects.ListObject> arraylist1;
	public static ArrayList<com.company.datarace.objects.ListObject> tsarraylist;
	public static HashMap<Object, Object> hashMap1;

	private static final Lock lock = new ReentrantLock();

	public static void initObjects() {

		arraylist1 = new ArrayList<com.company.datarace.objects.ListObject>();
		tsarraylist = new ArrayList<com.company.datarace.objects.ListObject>();
		hashMap1 = new HashMap<>();

	}

	// function to safely add to an arraylist
	// this should not lead to a TSV since we're using locks, so this is thread-safe

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
