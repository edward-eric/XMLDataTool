package org.data.xml.support.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropUtil {
	
	private static final String _delimiter = ".";
	
	private static final Properties _properties = new Properties();
	
	public PropUtil(){
		try {
			_properties.load(new InputStreamReader(getClass().getResourceAsStream("")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
