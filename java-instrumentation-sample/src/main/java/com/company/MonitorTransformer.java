package com.company;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.lang.Object;

import javassist.*;


public class MonitorTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println(className);
        if (className.equals("com/company/ClassToMonitor")){
            ClassPool pool = ClassPool.getDefault();
            try {
                CtClass cc = pool.get("com.company.ClassToMonitor");
                CtMethod method = cc.getDeclaredMethod("foo");
                method.insertBefore("com.company.Monitor.counter++");
                return cc.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                e.printStackTrace();
            }
            return classfileBuffer;

        }
        return null;
    }
}
