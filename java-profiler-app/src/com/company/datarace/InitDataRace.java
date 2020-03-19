package com.company.datarace;

import com.company.conf.Configuration;
import com.company.datarace.objects.ConcurrentObjects;
import com.company.datarace.tests.NormalThread;
import com.company.datarace.tests.TSVLockThread;
import com.company.datarace.tests.TSVThread;

/**
 * InitDataRace.java
 *
 * This triggers the threads and the TSVs
 */
public class InitDataRace {

	public static void datarace(/*String[] args*/) throws InterruptedException {

		ConcurrentObjects.initObjects();
		Configuration.init();
		System.out.println("\nCreated shared ArrayList with ObjectID: " + System.identityHashCode(ConcurrentObjects.arraylist1));
		System.out.println("\nCreated shared locked ArrayList with ObjectID: " + System.identityHashCode(ConcurrentObjects.tsarraylist));
		System.out.println("Created shared HashMap with ObjectID: " + System.identityHashCode(ConcurrentObjects.hashMap1));

		// total number of shared arraylist, normal and shared locked ArrayList threads to be spawned
		// overall, we spawn numThreads + 2 threads (two for the addDict and getDict threads)

		int numThreads = Integer.parseInt(Configuration.properties.getProperty("numthreads"));

		System.out.println("\nCreating " + (numThreads + 2) + " threads:");

		TSVThread[] TSVthreads = new TSVThread[numThreads];
		NormalThread[] normalThreads = new NormalThread[numThreads];
		TSVLockThread[] tsvLockThreads = new TSVLockThread[numThreads];

		int i = 0, j = 0, k = 0;

		// thread type (i.e scenario) is decided probabilistically here for better testing
		// for stress testing, we also tested creation individually for every scenario

		for(int n = 0; n < numThreads; n++) {

			double prob = Math.random();

			if(prob <= 0.33){
				normalThreads[i] = new NormalThread();
				normalThreads[i].start();
				i++;
			} else if (prob > 0.33 && prob <= 0.66){
				tsvLockThreads[k] = new TSVLockThread();
				tsvLockThreads[k].start();
				k++;
			}
			else {
				TSVthreads[j] = new TSVThread();
				TSVthreads[j].start();
				j++;
			}
		}

		// for simulating the HashMap (dict) TSV scenario
		com.company.datarace.InitDictTSV.dictTSV();

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
