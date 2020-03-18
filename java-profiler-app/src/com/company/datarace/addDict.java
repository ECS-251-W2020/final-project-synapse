package com.company.datarace;

import com.company.Global;
import com.company.datarace.objects.ConcurrentObjects;

import java.util.HashMap;

public class addDict extends Thread{
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Adding to shared dict");
        HashMap dict = ConcurrentObjects.hashMap1;
        int key = 0;
        int value = 5;
//        listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
        Global.threadcounter++;
        //System.out.println("#####Threadcounter value: "+Global.threadcounter);
        //TrapHandler.OnCall(String.valueOf(this.getId()) , "1" /*NW: TBD: change to objectId later when we have more objects*/, "add");
        dict.put(key, value);
    }
}
