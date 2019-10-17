package com.mip.software.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class IP {
	//根据key读取value
	 public static String readValue(String key) {
		 Properties prop=new Properties();  
		 try {  
		       InputStream is1=IP.class.getClassLoader().getResourceAsStream("service-resources.properties");  
		       prop.load(is1); 
		       String value = prop.getProperty (key);
		       is1.close();  
		       return value;
		    } catch (IOException e) {  
		        e.printStackTrace();  
		        return null;
		    }  
	 }
	//根据key读取value
	 public static String readValue2(String key) {
		 Properties prop=new Properties();  
		 try {  
			 String path = IP.class.getClass().getResource("/").getPath();
	            path = path.substring(1, path.indexOf("classes"));
	            prop.load(new FileInputStream(path + "config/service-resources.properties")); 
		       String value = prop.getProperty (key);
		       return value;
		    } catch (IOException e) {  
		        e.printStackTrace();  
		        return null;
		    }  
	 }
	 public static void main(String[] args) {
		System.out.println(readValue("queryCards"));
	}
	 
}
