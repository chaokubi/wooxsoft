package com.mip.software.common.util;

import java.lang.reflect.Field;
import java.util.Properties;

public class SystemInfo {
	
	public static String PROJECT_NAME;
	
	public static String DEFAULT_PASSWORD;
	
	public static String DEFAULT_CODE;
	
	public static Properties p = new Properties();

	static{
		load();
	}
	
	public static void load() {
		try {
			p.loadFromXML(Thread.currentThread().
					getContextClassLoader().getResourceAsStream("sysinfo.xml"));
			Field[] fileds = SystemInfo.class.getDeclaredFields();
			for (Field field : fileds) {
				if (null != p.getProperty(field.getName().toLowerCase())) {
					field.set(field, p.getProperty(field.getName().toLowerCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
