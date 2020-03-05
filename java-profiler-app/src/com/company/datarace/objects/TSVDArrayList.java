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
		TrapHandler.OnCall("1" /*change to actual threadid*/, "1" /*change to actual object id*/, "add");
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
