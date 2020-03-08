package com.tsvd;

import com.tsvd.trap.TrapHandler;

public class MyClass {
    public static void callInstrumentor(String threadNumber){
        System.out.println("This is where we call OnCall");
        TrapHandler.OnCall(threadNumber, "1" /*NW: TBD: change to actual object id*/, "add");

    }

}
