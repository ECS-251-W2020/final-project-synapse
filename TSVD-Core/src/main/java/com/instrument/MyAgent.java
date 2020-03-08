package com.instrument;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("Start!");
        inst.addTransformer(new MonitorTransformer());

    }


}