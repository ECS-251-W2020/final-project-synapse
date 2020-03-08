package datarace;

import java.sql.Timestamp;
import java.util.ArrayList;

import conf.Configuration;
import datarace.objects.ConcurrentObjects;
import datarace.objects.ListObject;

public class InitDataRace {

	public static void datarace(/*String[] args*/) throws InterruptedException {
		ConcurrentObjects.initObjects();
		System.out.println("Created aarraylist");
		Thread.sleep(1000);
		Configuration.init();
		ListObject listObject = new ListObject("samplestring1");
		listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
		ConcurrentObjects.arraylist1.add(listObject);
		int numThreads = Integer.valueOf((String) Configuration.properties.get("numthreads"));
		for(int n = 0; n < numThreads-1; n++) {
			Thread newthread = new TSVThread();
			newthread.start();
		}
		Thread.sleep(100);
		for(ListObject obj: ConcurrentObjects.arraylist1) {
			System.out.println("#####Object Add Timestamp: "+obj.getListAddtime());
		}
		
	}
}
