package datarace;

import java.util.ArrayList;

import datarace.objects.ConcurrentObjects;

public class InitDataRace {
	

	public static void main(String[] args) {
		ConcurrentObjects.initObjects();
		Thread thread2 = new TSVThread();
		thread2.start();
	}
}
