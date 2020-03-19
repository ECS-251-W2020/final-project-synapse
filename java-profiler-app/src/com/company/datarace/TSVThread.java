package com.company.datarace;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.company.Global;
import com.company.datarace.objects.ConcurrentObjects;
import com.company.datarace.objects.ListObject;
//import trap.TrapHandler;
import com.company.datarace.objects.TSVDArrayList;

public class TSVThread extends Thread{
	public void run() {

//		System.out.println("#####Added thread and attempting to create TSV");
		System.out.println(Thread.currentThread().getName() + " Working on shared arraylist");
		//ArrayList<ListObject> list = ConcurrentObjects.arraylist1;

//		System.out.println("\nList ObjectID: " +
//				System.identityHashCode(list) +
//				" Name: " +
//				list.getClass().getName());

		ListObject listObject = new ListObject("samplestring2");
		listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));

		Global.threadcounter++;

		//list.add(listObject);
		ConcurrentObjects.arraylist1.add(listObject);
	}
}
