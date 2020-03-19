package com.tsvd;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

/**
 * ThreadSafetyContract.java
 *
 * This consists the Thread Safety Contract to be used by the instrumenter,
 * and a function to parse it.
 */
public class ThreadSafetyContract {

    public static JSONObject object;

    public static void readTSC() throws Exception {

        String resourceName = "/tsc.json";
        InputStream inputStream = ThreadSafetyContract.class.getResourceAsStream(resourceName);

        if (inputStream == null) {
            throw new Exception("Cannot find resource file " + resourceName);
        }

        JSONTokener tokener = new JSONTokener(inputStream);
        object = new JSONObject(tokener);

    }

}
