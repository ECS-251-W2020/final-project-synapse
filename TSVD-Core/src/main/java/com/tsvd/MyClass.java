package com.tsvd;

import com.tsvd.trap.TrapHandler;

public class MyClass {
    public static void callInstrumentor(String threadNumber){

        System.out.println("This is where we call OnCall");

        try {
            System.out.println("Instrumentor: Sleeping now for 5 seconds .");
            java.lang.Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        TrapHandler.OnCall(threadNumber, "1" /*NW: TBD: change to actual object id*/, "add");

    }

}
