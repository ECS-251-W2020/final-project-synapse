package com.company;

import java.sql.Timestamp;

/**
 * Main.java
 *
 * Driver class
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        Global.initGlobal();

        com.company.datarace.InitDataRace.datarace();

        Timestamp endTime = new Timestamp(System.currentTimeMillis());

        System.out.println("Program execution time: " + (endTime.getTime() - startTime.getTime()));
    }

}