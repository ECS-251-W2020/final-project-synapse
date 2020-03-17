package com.tsvd;

import com.tsvd.trap.TrapHandler;

public class MyClass {
    public static void callInstrumenter(String threadNumber, String objId,  String operationId){

        TrapHandler.OnCall(threadNumber, objId, operationId);

    }
}
