package com.company;

//
//import datarace.InitDataRace;

public class Main {

    public static void main(String[] args) {
        System.out.println("before loop: ");
        ClassToMonitor classToMonitor = new ClassToMonitor();
        for (int i = 0; i < 10; i++) {
            classToMonitor.foo();
        }
        System.out.println("\nafter loop: " + Monitor.counter);
        //InitDataRace.mainStub();

    }

}