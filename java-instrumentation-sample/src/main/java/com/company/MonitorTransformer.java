package com.company;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;


public class MonitorTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        // className can be null, ignoring such classes.
        if (className == null) {
            return null;
        }

//        if (className.equals("com/company/Main")) {
//            ClassPool classPool = ClassPool.getDefault();

//        System.out.println(className);

//            to output all the methods used by the application
            try {
                ClassPool classPool = ClassPool.getDefault();
//                Javassist uses "." as a separator in class/package names.
                String classNameDots = className.replaceAll("/", ".");
                CtClass cc = classPool.get(classNameDots);
                for (CtMethod method : cc.getMethods()) {
                    method.instrument(
                            new ExprEditor() {
                                public void edit(MethodCall m) {
                                    String methodName = m.getClassName() + "." + m.getMethodName();
                                    if (methodName.equals("java.util.ArrayList.add") )
                                    System.out.println(methodName);
                                }
                            });
                }
            } catch (NotFoundException | CannotCompileException e) {
                e.printStackTrace();
            }


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

            // Behaviors == methods and constructors.
//            for (final CtMethod method : ctClass.getMethods()) {
////                    System.out.printf("%s - will collect metrics\n", method.getLongName());
//            }


//        if (className.equals("com/company/ClassToMonitor")){
////            System.out.println(className);
//            ClassPool pool = ClassPool.getDefault();
//            try {
////                System.out.print(pool);
//                CtClass cc = pool.get("com.company.ClassToMonitor");
//                CtMethod method = cc.getDeclaredMethod("foo");
//                method.insertBefore("com.company.Monitor.counter++;");
//                return cc.toBytecode();
//            } catch (NotFoundException | CannotCompileException | IOException e) {
////            } catch (NotFoundException e) {
//                e.printStackTrace();
////                System.out.println("Sum Ting Wong");
//            }
//            return classfileBuffer;
//
//        }
        return null;
    }
}
