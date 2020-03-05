package com.company;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.ClassFileTransformer;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyTransformer implements AgentBuilder.Transformer {

    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription,
                                            ClassLoader classLoader, JavaModule javaModule) {

        return builder.method(named("add"))
                .intercept(FixedValue.value("transformed"));
//        return null;
    }
}
