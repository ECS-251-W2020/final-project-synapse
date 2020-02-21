package trap;

import java.sql.Timestamp;

public class Trap {
	Timestamp createTime;
	String callingFunction;
	String callingThreadNum;
	String ObjectID;
	int delay;
	
	public Trap() {
		this.createTime = new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getCallingFunction() {
		return callingFunction;
	}
	public void setCallingFunction(String callingFunction) {
		this.callingFunction = callingFunction;
	}
	public String getCallingThreadNum() {
		return callingThreadNum;
	}
	public void setCallingThreadNum(String callingThreadNum) {
		this.callingThreadNum = callingThreadNum;
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
