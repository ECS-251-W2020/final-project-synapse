package datarace.objects;

import java.sql.Timestamp;

public class ListObject {
	String data;
	Timestamp creationTime;
	Timestamp listAddtime;
	
	public ListObject(String data) {
		this.data = data;
		creationTime = new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp getListAddtime() {
		return listAddtime;
	}
	public void setListAddtime(Timestamp listAddtime) {
		this.listAddtime = listAddtime;
	}
	public String getData() {
		return data;
	}
	public Timestamp getCreationTime() {
		return creationTime;
	}
	
}
