package com.mip.software.common.util;

import java.security.MessageDigest;
/**
 * md5加密辅助类
 * @author mip
 *
 */
public class MD5Util {

	private MD5Util() {}
	
	/**
	 * 加密方法
	 * @param inPutText 输入的需要加密的字符串
	 * @return
	 */
	public static String encrypt(String inPutText) throws Exception{
		String encryptText = null;
		if(inPutText == null || "".equals(inPutText.trim())) {
			throw new IllegalArgumentException("输入的加密内容为空，请检查.");
		}
		try {
			MessageDigest msgDigest = MessageDigest.getInstance("md5");//加密算法 MD5/SHA-1
			msgDigest.update(inPutText.getBytes("UTF8"));
			byte byteArr[] = msgDigest.digest();
			encryptText = hex(byteArr);
		} catch (Exception e) {
			throw e;
		}
		return encryptText;
	}

	/**
	 * 将字节数据装换为16进制字符串
	 * @param byteArr
	 * @return
	 */
	private static String hex(byte[] byteArr) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<byteArr.length; i++) {
			sb.append(Integer.toHexString((byteArr[i] & 0xFF) | 0x100).substring(1,3));//??
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("加密后的字符串:" + 
					MD5Util.encrypt("111111"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
