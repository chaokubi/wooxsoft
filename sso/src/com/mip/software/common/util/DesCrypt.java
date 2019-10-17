package com.mip.software.common.util;


import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Security;

import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * DES加密介绍 DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DesCrypt {
	public static final String KEY = "WJBZ#%&1";
	/** 编码格式 UTF-8 */
	public static final String CHARSET = "UTF-8";

	/**
	 * 加密
	 * 
	 * @param datasource
	 *            byte[]
	 * @param key
	 *            String
	 * @return byte[]
	 */
	public static String encrypt(String datasource, String key) {
		try {
			if (key.length() % 8 < 8)
				for (int i = key.length(); i < 8; i++)
					key += "0";
			DESKeySpec desKey = new DESKeySpec(key.getBytes(CHARSET));
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Security.addProvider(new BouncyCastleProvider());
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(key.getBytes(CHARSET));
			cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
			// 现在，获取数据并加密
			// 正式执行加密操作
			byte[] result = cipher.doFinal(datasource.getBytes(CHARSET));

			return new BASE64Encoder().encode(result);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 *
	 * @param src
	 *            byte[]
	 * @param key
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(String src, String key) throws Exception {
		// 创建一个DESKeySpec对象
		if (key.length() < 8)
			for (int i = key.length(); i < 8; i++)
				key += "0";
		DESKeySpec desKey = new DESKeySpec(key.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(key.getBytes(CHARSET));
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
		// 真正开始解密操作
		return cipher.doFinal(new BASE64Decoder().decodeBuffer(src));
	}

	//解码
	public static String jieMa(String str){
		String result=null;
		if(null!=str && !"".equals(str)){
			
			
//			String urldecode = URLDecoder.decode(str);
//			System.out.println("URLDecoder 解码后 ：" + urldecode);
			try {
				byte[] decryResult = DesCrypt.decrypt(str, DesCrypt.KEY);
				System.out.println("解密后：" + new String(decryResult));
				result=new String(decryResult);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// 待加密内容
		String src = "张三";
		System.out.println("原文：" + src);
		String result = DesCrypt.encrypt(src, DesCrypt.KEY);
		System.out.println("加密后：" + new String(result));
		
		String urlencoder = URLEncoder.encode(result);
		System.out.println("URLEncoder 编码后 ：" + urlencoder);
		
//		System.out.println("=====================");
//		String urldecode = URLDecoder.decode(urlencoder);
//		System.out.println("URLDecoder 解码后 ：" + urldecode);
//		try {
//			byte[] decryResult = DesCrypt.decrypt(urldecode, DesCrypt.KEY);
			System.out.println("解密后：" + jieMa(urlencoder));
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
	}
}