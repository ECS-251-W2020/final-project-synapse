package datarace;

import java.sql.Timestamp;
import java.util.ArrayList;

import datarace.objects.ConcurrentObjects;
import datarace.objects.ListObject;

public class InitDataRace {

	public static void main(String[] args) throws InterruptedException {
		ConcurrentObjects.initObjects();
		ListObject listObject = new ListObject("samplestring1");
		listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
		ConcurrentObjects.arraylist1.add(listObject);
		Thread thread2 = new TSVThread();
		thread2.start();
		Thread.sleep(100);
		for(ListObject obj: ConcurrentObjects.arraylist1) {
			System.out.println("Object Add Timestamp: "+obj.getListAddtime());
		}
		
	}
}
