package com.company;
import java.lang.instrument.Instrumentation;

public class MyFirstAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Start!");
    }
}