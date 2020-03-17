package com.company;

//
//import datarace.InitDataRace;

import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        Global.initGlobal();

        com.company.datarace.InitDataRace.datarace();

        Timestamp endTime = new Timestamp(System.currentTimeMillis());

        System.out.println(endTime.getTime() - startTime.getTime());
    }

}