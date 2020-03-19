package com.company.conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Configuration.java
 *
 * Used for setting config parameters
 */
public class Configuration {
	public static Properties properties;
	public static void init() {
		Properties prop = new Properties();
		Path userDir = Paths.get(System.getProperty("user.dir"));
		String fileName = Paths.get(userDir.toString(),"conf", "config.properties").toString();
		InputStream is = null;
		try {
		    is = new FileInputStream(fileName);
		} catch (FileNotFoundException ex) {
			System.out.println("Exception: " + ex);
		}
		try {
		    prop.load(is);
		} catch (IOException ex) {
			System.out.println("Exception: " + ex);
		}
		properties = prop;

	}
	

}
