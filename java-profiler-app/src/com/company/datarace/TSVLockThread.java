package com.company.datarace;

import com.company.Global;
import com.company.datarace.objects.ConcurrentObjects;
import com.company.datarace.objects.ListObject;

import java.sql.Timestamp;

/**
 * TSVLockThread.java
 *
 * This simulates adding to a global arraylist (ConcurrentObjects.tsarraylist).
 * However, since addToTSArrayList uses locks, this is a thread-safe add,
 * and hence will not lead to a TSV
 */

public class TSVLockThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Working on shared arraylist with locks");

        ListObject listObject = new ListObject("samplestring3");
        listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));

        Global.threadcounter++;

        ConcurrentObjects.addToTSArrayList(listObject);
    }
}
