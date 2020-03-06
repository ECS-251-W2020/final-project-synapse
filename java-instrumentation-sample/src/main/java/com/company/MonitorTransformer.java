package com.company;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.*;
import javassist.expr.*;


public class MonitorTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

//         className can be null, ignoring such classes.
        if (className == null) {
            return null;
        }

//        if (className.equals("com/company/Main")) {
//            ClassPool classPool = ClassPool.getDefault();

//        System.out.println(className);

//      to output all the methods used by the application

        ClassPool classPool = ClassPool.getDefault();

//      Javassist uses "." as a separator in class/package names.

        String classNameDots = className.replaceAll("/", ".");

//      Skip all agent classes
//
//        if (classNameDots.startsWith("com.company.MyFirstAgent")) {
//            return classfileBuffer;
//        }

//        Skip class if it doesn't belong to our Java app

        if (!classNameDots.startsWith("com.company")) {
            return classfileBuffer;
        }

        boolean flag = false;

            try {
                CtClass cc = classPool.get(classNameDots);
                for (CtMethod method : cc.getMethods()) {
//                    System.out.println(method.getName());
                    method.instrument(
                            new ExprEditor() {
                                public void edit(MethodCall m) throws CannotCompileException {
                                    String methodName = m.getClassName() + "." + m.getMethodName();
                                    if (methodName.equals("java.util.ArrayList.add") ) {
                                        System.out.println(methodName);
                                        String origMethodCall = "{ System.out.println(\"this is where we call onCall\"); $_ = $proceed($$);}";
                                        System.out.println(origMethodCall);
                                        String bodyToInsert = "System.out.println(\"this is where we call onCall\");";
                                        origMethodCall = bodyToInsert + origMethodCall;
                                        m.replace(origMethodCall);
                                    }
                                }
//                                public void edit(FieldAccess e) throws CannotCompileException {
//                                    System.out.println(e.getFieldName());
//
////                                    e.replace("{" + "$_ = $proceed($$);" + "System.out.println($_);" + "}");
//                                }
                            });
//                    method.instrument(
//                            new ExprEditor() {
//                                public void edit(MethodCall m)
//                                        throws CannotCompileException {
//                                    System.out.println(m.getClassName() + "." + m.getMethodName() + " " + m.getSignature());
//                                }
//                            });
                }
//                cc = classPool.get("java.util.ArrayList");
//                CtMethod DeclaredMethod = cc.getDeclaredMethod("add");
//                DeclaredMethod.insertBefore("System.out.println(\"this is where we call onCall\");");
//                return cc.toBytecode();

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
        return classfileBuffer;
    }
}
