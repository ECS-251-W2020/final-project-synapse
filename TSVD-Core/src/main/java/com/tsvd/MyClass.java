package com.tsvd;

import com.tsvd.trap.TrapHandler;

public class MyClass {
    public static void callInstrumentor(){
        System.out.println("This is where we call OnCall");
        TrapHandler.OnCall("1"/*String.valueOf(Thread.currentThread().getId())*/, "1" /*NW: TBD: change to actual object id*/, "add");

    }

}
