package trap;

import java.sql.Timestamp;

public class Trap {
	Timestamp createTime;
	String callingFunction;
	String ObjectID;
	int delay;
	String operationId;
	String threadId;
	
	public Trap(String thread_id, String objectId, String operation_id) {
		this.createTime = new Timestamp(System.currentTimeMillis());
		this.threadId = thread_id;
		this.ObjectID = objectId;
		this.operationId = operation_id;
	}
	
	public String getOperationId() {
		return operationId;
	}

	public String getThreadId() {
		return threadId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public String getCallingFunction() {
		return callingFunction;
	}
	public void setCallingFunction(String callingFunction) {
		this.callingFunction = callingFunction;
	}

	public String getObjectID() {
		return ObjectID;
	}
	public void setObjectID(String objectID) {
		ObjectID = objectID;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	
}
