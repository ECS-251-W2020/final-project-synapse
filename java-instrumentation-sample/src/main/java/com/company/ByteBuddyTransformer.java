package com.company;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.MemberSubstitution;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.ClassFileTransformer;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class ByteBuddyTransformer implements AgentBuilder.Transformer {

    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription,
                                            ClassLoader classLoader, JavaModule javaModule) {

        try {
            return builder.visit(MemberSubstitution.relaxed()
                    .method(named("add"))
                    .replaceWith(MyClass.class.getMethod("printLine"))
                    .on(any()));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

//        return builder.method(named("add"))
//                .intercept(FixedValue.value("transformed"));
//        return null;
        return builder;
    }
}
