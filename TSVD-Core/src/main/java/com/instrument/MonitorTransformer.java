package com.instrument;

import com.tsvd.MyClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.ClassFileTransformer;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.ProtectionDomain;
import java.util.Properties;

import com.tsvd.ThreadSafetyContract;
import javassist.*;
import javassist.expr.*;
import org.json.JSONObject;

import static com.tsvd.ThreadSafetyContract.*;


public class MonitorTransformer implements ClassFileTransformer {

    public String implementedClassName;

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

        if (classNameDots.startsWith("com.instrument")||classNameDots.startsWith("com.tsvd")) {
            return classfileBuffer;
        }

        try {
            CtClass cc = classPool.get(classNameDots);

//            //String userDir = Paths.get(System.getProperty("user.dir")).toString();
//            InputStream inputStream = this.getClass().getResourceAsStream("tsc.json");
//            //String contents = new String(Files.readAllBytes(fileName));
//
//            BufferedReader bR = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            StringBuilder responseStrBuilder = new StringBuilder();
//            while ((line = bR.readLine()) != null) {
//                responseStrBuilder.append(line);
//            }
//            inputStream.close();
//
//            JSONObject tsc = new JSONObject(responseStrBuilder.toString());

            //JSONObject tsc = new JSONObject(contents);
            //System.out.println(tsc.toString());
            for (CtMethod method : cc.getMethods()) {

//                System.out.println(method.getName());
                method.instrument(
                        new ExprEditor() {

                            public void edit(MethodCall m) throws CannotCompileException {

                                String methodName = m.getClassName() + "." + m.getMethodName();

//                                System.out.println(methodName);
//                                System.out.println(classNameDots + " " + methodName + " Line " + String.valueOf(m.getLineNumber())
//                                + " " + m.getFileName());

//                                if(methodName.startsWith("java.util.list")){
//
//                                    System.out.println(methodName);
//
//                                    String origMethodCall = "{$_ = $proceed($$);}";
//                                    String s = "System.out.println(\"instrumented 0: \" + ($0).getClass() );";
////                                    origMethodCall = "{" + s + origMethodCall + "}";
//                                    String classOfObject = "{ com.instrument.MonitorTransformer.implementedClassName = ($0).getClass().getName(); }"
//                                    + "{ System.out.println(\"instrumented 0: \" + com.instrument.MonitorTransformer.implementedClassName ); }";
//
//                                    String insertedMethodCall = "{" + s + origMethodCall + "}";
//
//                                    m.replace(insertedMethodCall);
//                                }




//                                    String origMethodCall = "{$_ = $proceed($$);}";
//                                    String printToInsert = "System.out.println(\"this is where we call onCall\"  + $_ = $proceed($$) );";
//
//                                    String classOfObject = "com.instrument.MonitorTransformer.implementedClassName = ($0).getClass().getName();";
//                                    +
//                                            "System.out.println(\"instrumented 0: \" + com.instrument.MonitorTransformer.implementedClassName );";
//
//                                    String bodyToInsert = "{ com.tsvd.MyClass.callInstrumenter(" +
//                                            "String.valueOf(Thread.currentThread().getName())," +
//                                            "Integer.toString(System.identityHashCode($0))," +
//                                            "\"" + methodName + "\"" +
//                                            ");} ";
//
//
////                                    String insertedMethodCall = "{" + bodyToInsert + origMethodCall + "}";
//
//                                    String insertedMethodCall = "{" + printToInsert + "}";

//                                    m.replace(insertedMethodCall);





//                                String operationID = "java.util.ArrayList.add";
//                                if (methodName.equals(operationID)) {
                                if(object.has(methodName)){

//                                    System.out.println(methodName);

                                    String origMethodCall = "{$_ = $proceed($$);}";
//                                        String printToInsert = "System.out.println(\"this is where we call onCall\");";
                                    String bodyToInsert = "{ com.tsvd.MyClass.callInstrumenter(" +
                                            "String.valueOf(Thread.currentThread().getName())," +
                                            "Integer.toString(System.identityHashCode($0))," +
                                            "\"" + methodName + "\"" +
                                            ");} ";

//                                    String s = "System.out.println($0);";

//                                    origMethodCall = "{" + bodyToInsert + origMethodCall + "}";

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
