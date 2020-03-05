package datarace;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.company.Global;
import datarace.objects.ConcurrentObjects;
import datarace.objects.ListObject;
import trap.TrapHandler;

public class TSVThread extends Thread{
	public void run() {
		System.out.println("Added thread and attempting to create TSV");
		ArrayList<ListObject> list = ConcurrentObjects.arraylist1;
		ListObject listObject = new ListObject("samplestring2");
		listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
		Global.threadcounter++;
		int threadnumber = Global.threadcounter;
		System.out.println("Threadcounter value: "+threadnumber);
		TrapHandler.OnCall(String.valueOf(threadnumber) , "1" /*NW: TBD: change to objectId later when we have more objects*/, "add");
		list.add(listObject);
	}
}
