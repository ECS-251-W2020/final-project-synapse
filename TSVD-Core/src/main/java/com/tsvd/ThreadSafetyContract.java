package com.tsvd;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadSafetyContract {

    public static JSONObject object;

    public static void readTSC() throws IOException {

        String resourceName = "/tsc.json";
        InputStream inputStream = ThreadSafetyContract.class.getResourceAsStream(resourceName);

        if (inputStream == null) {
            throw new NullPointerException("Cannot find resource file " + resourceName);
//            System.out.println("Cannot find resource file: " + resourceName);
        }

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
//
//  //        JSONObject tsc = new JSONObject(contents);
//        System.out.println(tsc.toString());

        JSONTokener tokener = new JSONTokener(inputStream);
        object = new JSONObject(tokener);

    }

}