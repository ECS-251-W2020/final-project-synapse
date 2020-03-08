package com.instrument;

import com.tsvd.MyClass;

import java.lang.instrument.ClassFileTransformer;
import java.io.IOException;
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

        ClassPool classPool = ClassPool.getDefault();

//      Javassist uses "." as a separator in class/package names.

        String classNameDots = className.replaceAll("/", ".");

//      Skip all agent classes

        if (classNameDots.startsWith("com.instrument.MyAgent")) {
            return classfileBuffer;
        }

        try {
            CtClass cc = classPool.get(classNameDots);
            for (CtMethod method : cc.getMethods()) {


//                    System.out.println(method.getName());
                method.instrument(
                        new ExprEditor() {

                            public void edit(MethodCall m) throws CannotCompileException {

                                String methodName = m.getClassName() + "." + m.getMethodName();

                                if (methodName.equals("java.util.ArrayList.add") ) {

//                                    System.out.println(methodName);

                                    String origMethodCall = "{$_ = $proceed($$);}";
//                                        String printToInsert = "System.out.println(\"this is where we call onCall\");";
                                    String bodyToInsert = "com.tsvd.MyClass.printLine();";

                                    origMethodCall = bodyToInsert + origMethodCall;
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
