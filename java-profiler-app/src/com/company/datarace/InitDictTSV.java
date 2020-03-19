package com.company.datarace;

/**
 * InitDictTSV.java
 *
 * Simulates a concurrent HashMap access, wherein one thread adds a (key, value) pair,
 * while another thread tries to search for a separate key.
 *
 * This is a TSV since adding to a HashMap requires usage in exclusive contexts.
 */
public class InitDictTSV {

	public static void dictTSV() throws InterruptedException {

		addDict t1 = new addDict();
		t1.start();

		getDict t2 = new getDict();
		t2.start();

		t1.join();
		t2.join();
		
	}
}
