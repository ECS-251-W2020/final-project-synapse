package com.company.conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {
	public static Properties properties;
	public static void init() {
		//numThreads = 2;
		Properties prop = new Properties();
		Path userDir = Paths.get(System.getProperty("user.dir"));
		System.out.println(userDir.toString());
		String fileName = Paths.get(userDir.getParent().toString(),"conf", "config.properties").toString();
		System.out.println(fileName);
		InputStream is = null;
		try {
		    is = new FileInputStream(fileName);
		} catch (FileNotFoundException ex) {
			System.out.println("Exception: "+ex);
		}
		try {
		    prop.load(is);
		} catch (IOException ex) {
			System.out.println("Exception: "+ex);
		}
		System.out.println("num threads: "+prop.getProperty("numthreads"));
		properties = prop;

	}
	

}
