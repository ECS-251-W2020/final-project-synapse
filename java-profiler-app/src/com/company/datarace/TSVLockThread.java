package com.company.datarace;

import com.company.Global;
import com.company.datarace.objects.ConcurrentObjects;
import com.company.datarace.objects.ListObject;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TSVLockThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Working on shared arraylist with locks");

//		System.out.println("\nList ObjectID: " +
//				System.identityHashCode(list) +
//				" Name: " +
//				list.getClass().getName());

        ListObject listObject = new ListObject("samplestring3");
        listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));

        Global.threadcounter++;

        ConcurrentObjects.addToTSArrayList(listObject);
    }
}
