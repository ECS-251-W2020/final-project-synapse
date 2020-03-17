package com.instrument;

import com.tsvd.ThreadSafetyContract;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) throws IOException {

        System.out.println("Start!");

//        inst.addTransformer(new MonitorTransformer());

        ThreadSafetyContract.readTSC();

    }


}