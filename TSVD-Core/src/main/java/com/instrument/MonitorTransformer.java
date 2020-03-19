package com.instrument;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import com.tsvd.ThreadSafetyContract;
import javassist.*;
import javassist.expr.*;

/**
 * MonitorTransformer.java
 *
 * This consists of the code that actually performs the instrumentation
 * Instrumentation is performed using javaassist
 * It reads the TSC file (tsc.json), and instruments all the parts in the code,
 * where they occur.
 * */
public class MonitorTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className == null) {
            return null;
        }

        ClassPool classPool = ClassPool.getDefault();

        // Javassist uses "." as a separator in class/package names.

        String classNameDots = className.replaceAll("/", ".");

        // Skips all classes within project

        if (classNameDots.startsWith("com.instrument")||classNameDots.startsWith("com.tsvd")) {
            return classfileBuffer;
        }

        try {
            CtClass cc = classPool.get(classNameDots);

            // iterate over all methods in the code

            for (CtMethod method : cc.getMethods()) {

                method.instrument(
                        new ExprEditor() {

                            public void edit(MethodCall m) throws CannotCompileException {

                                String methodName = m.getClassName() + "." + m.getMethodName();

                                // if we find a TSVD point in the code
                                // instrument it with a call to ProxyClass.callInstrumenter
                                // which calls the core library

                                if(ThreadSafetyContract.object.has(methodName)){

                                    String origMethodCall = "{$_ = $proceed($$);}";

                                    String bodyToInsert = "{ com.tsvd.ProxyClass.callInstrumenter(" +
                                            "String.valueOf(Thread.currentThread().getName())," +
                                            "Integer.toString(System.identityHashCode($0))," +
                                            "\"" + methodName + "\"" +
                                            ");} ";

                                    origMethodCall = "{" + bodyToInsert + origMethodCall + "}";

                                    m.replace(origMethodCall);
                                }

                            }
                        });
            }
            return cc.toBytecode();

        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}
