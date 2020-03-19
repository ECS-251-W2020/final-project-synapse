package com.company.datarace;

import java.sql.Timestamp;

import com.company.datarace.objects.ConcurrentObjects;
import com.company.datarace.objects.ListObject;

public class InitDataRace {

	public static void datarace(/*String[] args*/) throws InterruptedException {

		ConcurrentObjects.initObjects();
		System.out.println("\nCreated shared ArrayList with ObjectID: " + System.identityHashCode(ConcurrentObjects.arraylist1));
		System.out.println("Created shared HashMap with ObjectID: " + System.identityHashCode(ConcurrentObjects.hashMap1));

//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

//		Configuration.init();

		ListObject listObject = new ListObject("samplestring1");
		listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
		//ConcurrentObjects.arraylist1.add(listObject);
//		int numThreads = Integer.valueOf((String) Configuration.properties.get("numthreads"));

//		System.out.println("num threads: " + numThreads);

		int numThreads = 5;

		System.out.println("\nCreating " + (numThreads + 2) + " threads:");

		TSVThread[] TSVthreads = new TSVThread[numThreads];
		NormalThread[] normalThreads = new NormalThread[numThreads];
		TSVLockThread[] tsvLockThreads = new TSVLockThread[numThreads];

		int i=0, j=0, k=0;

		for(int n = 0; n < numThreads; n++) {

			double prob = Math.random();

			if(prob <= 0.33){
				normalThreads[i] = new com.company.datarace.NormalThread();
				normalThreads[i].start();
				i++;
			} else if (prob > 0.33 && prob <= 0.66){
				tsvLockThreads[k] = new com.company.datarace.TSVLockThread();
				tsvLockThreads[k].start();
				k++;
			}
			else {
				TSVthreads[j] = new com.company.datarace.TSVThread();
				TSVthreads[j].start();
				j++;
			}
		}

		com.company.datarace.InitDictTSV.dictTSV();

//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		for(int n = 0; n < i; n++) {
			normalThreads[n].join();
		}
		for(int n = 0; n < j; n++) {
			TSVthreads[n].join();
		}
		for(int n = 0; n < k; n++) {
			tsvLockThreads[n].join();
		}
		
	}
}
