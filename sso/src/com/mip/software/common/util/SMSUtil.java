package com.mip.software.common.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class SMSUtil {

	private static SMSUtil instance=new SMSUtil();
	
	public static SMSUtil getInstance(){
		if(instance==null){
			synchronized (SMSUtil.class) {
				if(instance == null){
					instance=new SMSUtil();
				}
			}
		}
		return instance;
	}
	
	private SMSUtil(){
		
	}
	
	public String sendMsg(String phone,String content) throws Exception{
		String url=IP.readValue("iflytek_sendMsg");
		Map<String,String> params=new HashMap<String, String>();
		params.put("phone", phone);
		params.put("content", new String(content.getBytes(), "UTF-8"));
		
		Map<String,String> obj=new HashMap<String,String>();
		obj.put("sysCode", AESUtil.encrypt("SWJAPP"));
		obj.put("data", AESUtil.encrypt(JSONObject.toJSONString(params)));
		
		String result=HttpXmlUtil.doPost(url, obj);
		return result;
	}
//	public static String sendMsg(String phone,String content) throws Exception{
//		String url=ConfigLoadUtil.loadConfig().getPropertie("iflytek_sendMsg");
//		
//		Map<String,String> params=new HashMap<String, String>();
//		params.put("phone", phone);
//		params.put("content", new String(content.getBytes(), "UTF-8"));
//		
//		Map<String,String> obj=new HashMap<String,String>();
//		obj.put("sysCode", AESUtil.encrypt("SWJAPP"));
//		obj.put("data", AESUtil.encrypt(JSONObject.toJSONString(params)));
//		
//		String result=com.netbull.shop.util.HttpXmlUtil.doPost(url, obj);
//		return result;
//	}
	
	public static void main(String[] args) throws Exception {
		int veriCode=(int)((Math.random()*9+1)*100000);
		String phoneNm="18056019679";
		String s1="您在我家亳州平台租车交付定金的支付验证码是:" + veriCode + ",请保管好该验证码，验证码有效时间为半小时!";

		System.out.println("=====phoneNm========="+phoneNm);
		String ret=SMSUtil.getInstance().sendMsg(phoneNm,s1 );
		System.out.println("======ret==========="+ret);
	}
}
