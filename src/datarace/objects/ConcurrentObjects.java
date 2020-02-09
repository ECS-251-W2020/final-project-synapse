package datarace.objects;

import java.util.ArrayList;

public class ConcurrentObjects {
	public static ArrayList<String> arraylist1;
	public static void initObjects() {
		arraylist1 = new ArrayList<String>();
		arraylist1.add("samplestring1");
	}


}
