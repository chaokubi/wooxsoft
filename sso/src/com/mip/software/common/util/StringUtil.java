package com.mip.software.common.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {

	private static final String UTF8 = "UTF-8";

	private static final String ISO88591 = "ISO-8859-1";

	/**
	 * 鍒ゆ柇浼犲叆镄勬暟缁勬槸鍚︿负绌?
	 * 
	 * @param objs
	 * @return
	 */
	public static boolean isEmpty(Object[] objs) {
		return (objs == null) || (objs.length <= 0);
	}
	
	/**
	   * <p>方法说明:判断字符串是否为空.</p> 
	   * <p>创建时间:2011-10-25上午12:16:03</p>
	   * <p>返回值:不为空返回true,反之返回false.</p> 
	   * @param str 判断字符串
	   * @return boolean
	  */
	 public static boolean isEmpty(final String str) {
		 boolean rs = false;
		 if (str != null && str.trim().length() > 0) {
			 rs = true;
		 }
		 return rs;
	 }
	
	public static boolean validateString(String obj) {
		return NullUtil.isNull(obj) ? false : true;
	}

	/**
	 * 铡绘�?bj镄勭┖鐧藉瓧�?濡傛灉str涓簄ull鍒栾繑锲?"
	 * */
	public static String getString(String obj) {
		return NullUtil.isNull(obj) ? "" : obj.trim();
	}

	/**
	 * 铡绘�?bj镄勭┖鐧藉瓧�?濡傛灉str涓簄ull鍒栾繑锲?"
	 * */
	public static String getString(Object obj) {
		return NullUtil.isNull(obj) ? "" : obj.toString().trim();
	}

	/**
	 * @param str
	 *            婧愬瓧绗︿覆榛樿涓篒SO-8859-1
	 * @return 杩斿洖杞崲鍚庣殑UTF-8瀛楃涓?
	 */
	public static String converISOToUTF8(String str) {
		try {
			return NullUtil.isNull(str) ? null : new String(str
					.getBytes(ISO88591), UTF8);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * @param str
	 *            婧愬瓧绗︿覆榛樿涓篒SO-8859-1
	 * @return 杩斿洖杞崲鍚庣殑code瀛楃涓?
	 */
	public static String converISOToCode(String str, String code) {
		if (NullUtil.isNull(str))
			return "";
		try {
			return new String(str.getBytes(ISO88591), code);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * @param str
	 *            婧愬瓧绗︿覆 code1 婧愬瓧绗︾紪�?code2 鐩爣�?楃缂栫�?
	 * @return 杩斿洖杞崲鍚庣殑�?楃�?
	 */
	public static String converCode1ToCode1(String str, String code1,
			String code2) {
		if (NullUtil.isNull(str))
			return "";
		try {
			return new String(str.getBytes(code1), code2);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * 杩斿洖鎸囧畾�?��害镄勫瓧�?瓒呰绷璇ラ昵搴︾殑锷?..缁揿�?
	 * 
	 * @param str
	 *            - 瑕佸鐞嗙殑瀛楃涓?
	 * @param len
	 *            - �?���?
	 * */
	public static String subString(String str, int len) {
		if (NullUtil.isNull(str)) {
			return "";
		}
		if (str.length() > len) {
			return str.substring(0, len) + "...";
		} else {
			return str;
		}
	}
	
	public static String moveNullString(Object str){
		return str==null?"":(String)str;
	}
	
	public static int parseInt(String s, int def) {
		try{
			return Integer.parseInt(s);
		}catch(Exception e) {
			return def;
		}
	}
}
