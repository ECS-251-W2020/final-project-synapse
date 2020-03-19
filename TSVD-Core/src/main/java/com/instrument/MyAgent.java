package com.instrument;

import com.tsvd.ThreadSafetyContract;
import java.lang.instrument.Instrumentation;

/**
 * MyAgent.java
 *
 * This class instantiates the tsvd4j instrumentation agent
 */
public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) throws Exception {

        System.out.println("tsvd4j: Start!");

        ThreadSafetyContract.readTSC();

        inst.addTransformer(new MonitorTransformer());

    }


}