package com.mip.software.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {
	
	private static final String SECRETKEY = "E9DA036F58B724C1";
	
	public static String encrypt(String data) {
		return encrypt(data, SECRETKEY);
	}
		
	public static String decrypt(String data) {
		return decrypt(data, SECRETKEY);
	}

	public static String encrypt(String data, String key) {
		try {
			String iv = "0123456789abcdef";

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();

			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength
						+ (blockSize - (plaintextLength % blockSize));
			}

			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			return new String(new Base64().encode(encrypted));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String decrypt(String data, String key) {
		try{
			String iv = "0123456789abcdef";

			byte[] encrypted1 = new Base64().decode(data.getBytes());
			 
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			             
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			  
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString.trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
