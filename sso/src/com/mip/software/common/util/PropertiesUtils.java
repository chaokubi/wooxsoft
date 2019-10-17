package com.mip.software.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	//根据key读取value
	 public static String readValue(String key) {
		 Properties prop=new Properties();  
		 try {  
		       InputStream is1=PropertiesUtils.class.getClassLoader().getResourceAsStream("../config/service-resources.properties");  
		       prop.load(is1); 
		       String value = prop.getProperty (key);
		       is1.close();  
		       return value;
		    } catch (IOException e) {  
		        e.printStackTrace();  
		        return null;
		    }  
	 }
	 public static void main(String[] args) {
		System.out.println(readValue("locale"));
	}
	 
}
