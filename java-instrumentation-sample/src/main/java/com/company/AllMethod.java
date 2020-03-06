package com.company;

import net.bytebuddy.asm.Advice;

public class AllMethod {

    @Advice.OnMethodEnter
    static void getAllMethods(@Advice.Origin String method, @Advice.Origin Class klass) throws Exception {

        System.out.println(method);
        System.out.println(klass.getName());
    }

}