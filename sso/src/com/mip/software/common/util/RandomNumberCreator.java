package com.mip.software.common.util;

import java.util.Random;

public class RandomNumberCreator {

	public static String create(){
		
		StringBuffer sb=new StringBuffer();
		sb.append("SWJPAY");
		for(int i=0;i<8;i++){
			sb.append(new Random().nextInt(10));
		}
		return sb.toString();
	}
}
