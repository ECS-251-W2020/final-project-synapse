package com.company.datarace;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.company.conf.Configuration;
import com.company.datarace.objects.ConcurrentObjects;
import com.company.datarace.objects.ListObject;

public class InitDataRace {

	public static void datarace(/*String[] args*/) throws InterruptedException {

		ConcurrentObjects.initObjects();
		System.out.println("Created arraylist");

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Configuration.init();

		ListObject listObject = new ListObject("samplestring1");
		listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
		//ConcurrentObjects.arraylist1.add(listObject);
		int numThreads = Integer.valueOf((String) Configuration.properties.get("numthreads"));

		System.out.println("num threads: " + numThreads);

		for(int n = 0; n < numThreads; n++) {
			Thread.sleep(10);
			Thread newthread = new com.company.datarace.TSVThread();
			newthread.start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(ListObject obj: ConcurrentObjects.arraylist1) {
			System.out.println("#####Object Add Timestamp: " + obj.getListAddtime());
		}
		
	}
}
