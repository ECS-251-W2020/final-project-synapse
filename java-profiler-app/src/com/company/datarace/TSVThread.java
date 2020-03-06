package datarace;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.company.Global;
import datarace.objects.ConcurrentObjects;
import datarace.objects.ListObject;
import trap.TrapHandler;
import datarace.objects.TSVDArrayList;

public class TSVThread extends Thread{
	public void run() {
		System.out.println("#####Added thread and attempting to create TSV");
		TSVDArrayList list = ConcurrentObjects.arraylist1;
		ListObject listObject = new ListObject("samplestring2");
		listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
		Global.threadcounter++;
		//System.out.println("#####Threadcounter value: "+Global.threadcounter);
		//TrapHandler.OnCall(String.valueOf(this.getId()) , "1" /*NW: TBD: change to objectId later when we have more objects*/, "add");
		list.add(listObject);
	}
}
