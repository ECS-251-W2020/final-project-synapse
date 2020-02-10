package datarace.objects;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ConcurrentObjects {
	public static ArrayList<ListObject> arraylist1;
	public static void initObjects() {
		arraylist1 = new ArrayList<ListObject>();
		
		
	}
}
