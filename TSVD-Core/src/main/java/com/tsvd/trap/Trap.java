package com.tsvd.trap;

import java.sql.Timestamp;

/**
 * Trap.java
 *
 * This class defines an entry (i.e a trap) in the trap set.
 *
 * Each trap is defined by the tuple (thread_id, objectId, operation_id), depicting
 * the thread creating it, and the object and method associated with it.
 *
 */
public class Trap {

	private Timestamp createTime;

	private String callingFunction;

	private String objectID;

	private String operationId;

	private String threadId;

	private int delay;
	
	public Trap(String thread_id, String objectId, String operation_id) {
		this.createTime = new Timestamp(System.currentTimeMillis());
		this.threadId = thread_id;
		this.objectID = objectId;
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
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
