package com.company.datarace.tests;

import java.sql.Timestamp;

import com.company.Global;
import com.company.datarace.objects.ConcurrentObjects;
import com.company.datarace.objects.ListObject;

/**
 * TSVThread.java
 *
 * This simulates addition to a global arraylist (ConcurrentObjects.arraylist1).
 * Since we directly use ArrayList.add, which is thread-unsafe, this leads to a TSV
 */

public class TSVThread extends Thread{
	public void run() {

		System.out.println(Thread.currentThread().getName() + " Working on shared arraylist");

		ListObject listObject = new ListObject("samplestring2");
		listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));

		Global.threadcounter++;

		ConcurrentObjects.arraylist1.add(listObject);
	}
}
