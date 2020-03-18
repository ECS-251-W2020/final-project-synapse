package com.company.datarace;

public class InitDictTSV {

	public static void dictTSV() throws InterruptedException {

//		ConcurrentObjects.initObjects();
//		System.out.println("\nCreated a HashMap to be shared by threads");

//		System.out.println("\nCreating " + 2 + " threads:");


		addDict t1 = new addDict();
		t1.start();

		getDict t2 = new getDict();
		t2.start();

		t1.join();
		t2.join();
		
	}
}
