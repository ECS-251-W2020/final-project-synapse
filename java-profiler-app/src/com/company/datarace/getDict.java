package com.company.datarace;

import com.company.Global;
import com.company.datarace.objects.ConcurrentObjects;

import java.util.HashMap;

public class getDict extends Thread{
    public void run() {
        System.out.println(Thread.currentThread().getName() + " containsKey from shared dict");
        HashMap dict = ConcurrentObjects.hashMap1;
        int key = 0;
        boolean flag;
//        listObject.setListAddtime(new Timestamp(System.currentTimeMillis()));
        Global.threadcounter++;
        //System.out.println("#####Threadcounter value: "+Global.threadcounter);
        //TrapHandler.OnCall(String.valueOf(this.getId()) , "1" /*NW: TBD: change to objectId later when we have more objects*/, "add");
        flag = dict.containsKey(key);
    }
}
