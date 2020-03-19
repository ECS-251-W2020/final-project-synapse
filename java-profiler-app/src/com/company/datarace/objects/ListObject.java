package com.company.datarace.objects;

import java.sql.Timestamp;

/**
 * ListObject.java
 *
 * Specifies the type of object to be added into the arraylists
 */
public class ListObject {
	private String data;
	private Timestamp creationTime;
	private Timestamp listAddtime;
	
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

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public void setData(String data) {
		this.data = data;
	}
}
