package conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class Configuration {
	public static Properties properties;
	public static void init() {
		//numThreads = 2;
		Properties prop = new Properties();
		System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir")+"\\conf\\config.properties";
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
