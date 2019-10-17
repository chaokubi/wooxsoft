package com.mip.software.common.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESEnDecoder {
	
	/**
	 * @Title: encrypt
	 * @Description: 数据加密
	 * @param: content 待加密内容
	 * @param: key 密钥
	 * @return: String
	 * @throws
	 */
	public static String encrypt(String content, String key) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] keyBytes = toKey(key);
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] dataBytes = content.getBytes("utf-8");
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			byte[] result = cipher.doFinal(plaintext);
			return parseByte2HexStr(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Title: decrypt
	 * @Description: 解密操作
	 * @param: content 待解密内容
	 * @param: key 密钥
	 * @return: String
	 * @throws
	 */
	public static String decrypt(String content, String key) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			byte[] keyBytes = toKey(key);
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] result = cipher.doFinal(parseHexStr2Byte(content));
			return new String(result).trim();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: parseHexStr2Byte
	 * @Description: 将二进制转成16进制
	 * @param: buf
	 * @return: String
	 * @throws
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Title: parseHexStr2Byte
	 * @Description: 将16进制转成2进制
	 * @param: hexStr
	 * @return: byte[]
	 * @throws
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * @Title: toKey
	 * @Description: 密钥转换
	 * @param: key
	 * @return: byte[]
	 * @throws
	 */
	private static byte[] toKey(String key) {
		if (key.length() == 16) {
			return key.getBytes();
		} else if (key.length() < 16) {
			byte[] inBytes = new byte[16];
			System.arraycopy(key.getBytes(), 0, inBytes, 0, key.getBytes().length);
			return inBytes;
		} else {
			return key.substring(0, 16).getBytes();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("----"+decrypt("75CBD7E4AF84013C1B3EA9FFC4F95FC4","YQ"));
		System.out.println("----"+encrypt("admin7516","YQ"));
	}
	
}
