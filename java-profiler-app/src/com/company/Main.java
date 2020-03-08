package com.company;

//
//import datarace.InitDataRace;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Global.initGlobal();
        System.out.println("before loop: ");
        ClassToMonitor classToMonitor = new ClassToMonitor();
        for (int i = 0; i < 1; i++) {
//            classToMonitor.foo();
        //    classToMonitor.bar();
        }
        datarace.InitDataRace.datarace();
        System.out.println("\nafter loop: " + Monitor.counter);
        //InitDataRace.mainStub();

//        ClassToMonitor classToMonitor = new ClassToMonitor();
 //       classToMonitor.bar();
    }

}