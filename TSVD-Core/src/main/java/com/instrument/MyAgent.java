package com.instrument;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) throws IOException {

        System.out.println("Start!");

                    //String userDir = Paths.get(System.getProperty("user.dir")).toString();
            InputStream inputStream = this.getClass().getResourceAsStream("tsc.json");
            //String contents = new String(Files.readAllBytes(fileName));

            BufferedReader bR = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuilder responseStrBuilder = new StringBuilder();
            while ((line = bR.readLine()) != null) {
                responseStrBuilder.append(line);
            }
            inputStream.close();

            JSONObject tsc = new JSONObject(responseStrBuilder.toString());
//
//        JSONObject tsc = new JSONObject(contents);
        System.out.println(tsc.toString());

        inst.addTransformer(new MonitorTransformer());

    }


}