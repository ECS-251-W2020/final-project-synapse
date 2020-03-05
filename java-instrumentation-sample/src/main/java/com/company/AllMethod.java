package com.company;

import net.bytebuddy.asm.Advice;

public class AllMethod {

    @Advice.OnMethodEnter
    static void getAllMethods(@Advice.Origin String method) throws Exception {

        System.out.println(method);
    }

}