package com.mip.software.common.util;

import java.io.Serializable;

public class ExcelVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3696900897758752337L;
	
	private Integer i;
	private String name;
	public Integer getI() {
		return i;
	}
	public void setI(Integer i) {
		this.i = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
