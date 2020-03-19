package com.company.datarace.tests;

import com.company.Global;
import com.company.datarace.objects.ConcurrentObjects;

import java.util.HashMap;

/**
 * getDict.java
 *
 * Simulates access from a HashMap, as depicted in the concurrent HashMap access scenario
 *
 * To simulate the TSV, we search for a separate key from the one used in addDict
 */

public class getDict extends Thread{
    public void run() {
        System.out.println(Thread.currentThread().getName() + " containsKey from shared dict");
        int key = 0;
        Global.threadcounter++;
        System.out.println("containsKey: " + ConcurrentObjects.hashMap1.containsKey(key));
    }
}
