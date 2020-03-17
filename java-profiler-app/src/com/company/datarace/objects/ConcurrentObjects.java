package com.company.datarace.objects;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentObjects {

	public static ArrayList<com.company.datarace.objects.ListObject> arraylist1;

	public static void initObjects() {

		arraylist1 = new ArrayList<com.company.datarace.objects.ListObject>();
		
	}
}
