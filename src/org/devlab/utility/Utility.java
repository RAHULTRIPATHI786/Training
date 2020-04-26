package org.devlab.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utility {

	
	public static String getpropertyFromPropertyfile(String propertname) throws IOException {
		
		// creating filereader object 
		FileReader flereader = new FileReader("Config.properties");
		
		// creating instance of properties class object 
		Properties prop = new Properties();
		
		
		//passing the property file to Properties object
		prop.load(flereader);
		
		// returning value from the property file
		return prop.getProperty(propertname);
		
				
		
		
		
	
	}
}
