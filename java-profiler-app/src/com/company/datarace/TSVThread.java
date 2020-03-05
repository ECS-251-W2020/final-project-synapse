package datarace;

import java.sql.Timestamp;
import java.util.ArrayList;

import datarace.objects.ConcurrentObjects;
import datarace.objects.ListObject;
import trap.TrapHandler;

public class TSVThread extends Thread{
	public void run() {
		System.out.println("Added thread and attempting to create TSV");
		ArrayList<ListObject> list = ConcurrentObjects.arraylist1;
		ListObject listObject = new ListObject("samplestring2");
		listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
		TrapHandler.OnCall("1" /*change to thread ID later */, "1" /*change to objectId later*/, "add");
		list.add(listObject);
	}
}
