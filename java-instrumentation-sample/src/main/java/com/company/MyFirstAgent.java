package com.company;
import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.matcher.ElementMatchers;

public class MyFirstAgent {

    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("Start!");
//        inst.addTransformer(new MonitorTransformer());
//        inst.addTransformer(new byteBuddyTransformer);
        new AgentBuilder.Default()
                .type(ElementMatchers.any())
                .transform(new ByteBuddyTransformer())
                .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .installOn(inst);
    }

}