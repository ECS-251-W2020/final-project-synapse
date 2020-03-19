package com.tsvd;

import com.tsvd.trap.TrapHandler;

/**
 * ProxyClass.java
 *
 * This consists of the proxy call to the main algorithm, called by the instrumenter
 */

public class ProxyClass {
    public static void callInstrumenter(String threadNumber, String objId,  String operationId){

        TrapHandler.OnCall(threadNumber, objId, operationId);

    }
}
