package com.tsvd;

import com.tsvd.trap.TrapHandler;

public class MyClass {
    public static void callInstrumenter(String threadNumber, String objId,  String operationId){
        //System.out.println("opid recieved: "+operationId);
//        System.out.println("This is where we call OnCall");

//        try {
//            System.out.println("Instrumenter: Thread " + threadNumber + " Sleeping for 5 seconds before we call OnCall().");
//            java.lang.Thread.sleep(5000);
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        TrapHandler.OnCall(threadNumber, objId/* "1" NW: TBD: change to actual object id*/, operationId);

    }

}
