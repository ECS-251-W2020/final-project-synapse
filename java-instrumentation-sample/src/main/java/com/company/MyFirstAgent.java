package com.company;
import java.lang.instrument.Instrumentation;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.agent.builder.AgentBuilder;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

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

//        new AgentBuilder.Default()
//                .with(new AgentBuilder.InitializationStrategy.SelfInjection.Eager())
////                .type((ElementMatchers.any()))
//                .type((ElementMatchers.nameStartsWith("com.company")))
//                .transform((builder, typeDescription, classLoader, module) -> builder.method(ElementMatchers.any())
//                .intercept(Advice.to(AllMethod.class))
//        ).installOn(inst);

//        Class<?> dynamicType = new ByteBuddy()
//                .subclass(Object.class)
//                .method(ElementMatchers.named("add"))
//                .intercept(FixedValue.value("Hello! Instrumented"))
//                .make()
//                .load

    }


}