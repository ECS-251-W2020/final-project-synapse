package datarace.objects;

import java.sql.Timestamp;
import java.util.ArrayList;

import trap.TrapHandler;

public class TSVDArrayList extends ArrayList<ListObject> {
	String objectId;
	Timestamp lastReadTime;
	Timestamp lastWriteTime;
	
	TSVDArrayList(){
		super();
	}
	
	@Override
	public boolean add(ListObject obj) {
		//System.out.println("Threadid from thread.getcurentthrad: "+Thread.currentThread().getId());
		TrapHandler.OnCall(String.valueOf(Thread.currentThread().getId()), "1" /*NW: TBD: change to actual object id*/, "add");
		this.lastWriteTime = new Timestamp(System.currentTimeMillis());
		return super.add(obj);
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public Timestamp getLastReadTime() {
		return lastReadTime;
	}
	public Timestamp getLastWriteTime() {
		return lastWriteTime;
	}

}
