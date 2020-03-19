package com.company.datarace;

import com.company.Global;
import com.company.datarace.objects.ConcurrentObjects;

/**
 * addDict.java
 *
 * Simulates adding to a HashMap, as depicted in the concurrent HashMap scenario
 */
public class addDict extends Thread{
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Adding to shared dict");
        int key = 1;
        int value = 5;
        Global.threadcounter++;
        ConcurrentObjects.hashMap1.put(key, value);
    }
}
