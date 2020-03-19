package com.company.datarace.tests;

import com.company.Global;

import java.util.ArrayList;

/**
 * NormalThread.java
 *
 * This simulates a case where threads create their own lists and add to them.
 * Since threads only access their own copies of the list, this won't lead to a TSV.
 */

public class NormalThread extends Thread{
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Working on private objects");
        ArrayList<Integer> list = new ArrayList<Integer>(5);
        int listInt = 4;
        Global.threadcounter++;
        list.add(listInt);
    }
}
