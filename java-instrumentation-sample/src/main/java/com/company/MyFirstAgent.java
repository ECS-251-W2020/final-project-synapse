package com.company;
import java.lang.instrument.Instrumentation;

import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.agent.builder.AgentBuilder;

public class MyFirstAgent {

    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("Start!");
        inst.addTransformer(new MonitorTransformer());
//        new AgentBuilder.Default()
//                .type(ElementMatchers.any())
//                .transform(new ByteBuddyTransformer())
//                .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
//                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
//                .installOn(inst);
    }

}