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

//        ClassPool classPool = ClassPool.getDefault();

//        System.out.println(className);

        // className can be null, ignoring such classes.
        if (className == null) {
            return null;
        }
//
//        // Javassist uses "." as a separator in class/package names.
//        final String classNameDots = className.replaceAll("/", ".");
//        final CtClass ctClass = classPool.getOrNull(classNameDots);
//
//        // Won't find some classes from java.lang.invoke,
//        // but we're not interested in them anyway.
//        if (ctClass == null) {
//            return null;
//        }
//
//        // A frozen CtClass is a CtClass
//        // that was already converted to Java class.
//        if (ctClass.isFrozen()) {
//            // No longer need to keep the CtClass object in memory.
//            ctClass.detach();
//            return null;
//        }

        if (className.equals("com/company/ClassToMonitor")){
//            System.out.println(className);
            ClassPool pool = ClassPool.getDefault();
            try {
                System.out.print(pool);
                CtClass cc = pool.get("com.company.ClassToMonitor");
                CtMethod method = cc.getDeclaredMethod("foo");
                method.insertBefore("com.company.Monitor.counter++;");
                return cc.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
//            } catch (NotFoundException e) {
                e.printStackTrace();
//                System.out.println("Sum Ting Wong");
            }
            return classfileBuffer;

        }
        return null;
    }
}
