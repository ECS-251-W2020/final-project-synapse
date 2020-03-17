package com.company.datarace;

import com.company.Global;

import java.util.ArrayList;

public class NormalThread extends Thread{
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Working on private objects");
        ArrayList<Integer> list = new ArrayList<Integer>(5);
        int listInt = 4;
//        listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
        Global.threadcounter++;
        //System.out.println("#####Threadcounter value: "+Global.threadcounter);
        //TrapHandler.OnCall(String.valueOf(this.getId()) , "1" /*NW: TBD: change to objectId later when we have more objects*/, "add");
        list.add(listInt);
    }
}
