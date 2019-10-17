package com.mip.software.common.util.crypt;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;

public class RSAUtil {
	
	/**
	 * 生成公钥和私钥
	 * 
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		map.put("public", publicKey);
		map.put("private", privateKey);
		return map;
	}

	/**
	 * 使用模和指数生成RSA公钥
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding】
	 * 
	 * @param modulus 模
	 * @param exponent 指数
	 * @return
	 */
	public static RSAPublicKey getPublicKey(String modulus, String exponent) {
		try {
			BigInteger b1 = new BigInteger(modulus);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用模和指数生成RSA私钥
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding】
	 * 
	 * @param modulus 模
	 * @param exponent 指数
	 * @return
	 */
	public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {
		try {
			BigInteger b1 = new BigInteger(modulus);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String data, RSAPublicKey publicKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		// 模长
		int key_len = publicKey.getModulus().bitLength() / 8;
		// 加密数据长度 <= 模长-11
		String[] datas = splitString(data, key_len - 11);
		String mi = "";
		// 如果明文长度大于模长-11则要分组加密
		for (String s : datas) {
			mi += bcd2Str(cipher.doFinal(s.getBytes()));
		}
		return mi;
	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String data,
			RSAPrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		// 模长
		int key_len = privateKey.getModulus().bitLength() / 8;
		byte[] bytes = data.getBytes();
		byte[] bcd = ASCII_To_BCD(bytes, bytes.length);
		System.err.println(bcd.length);
		// 如果密文长度大于模长则要分组解密
		String ming = "";
		byte[][] arrays = splitArray(bcd, key_len);
		for (byte[] arr : arrays) {
			ming += new String(cipher.doFinal(arr));
		}
		return ming;
	}

	/**
	 * ASCII码转BCD码
	 * 
	 */
	public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	public static byte asc_to_bcd(byte asc) {
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	/**
	 * BCD转字符串
	 */
	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;

		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}

	/**
	 * 拆分字符串
	 */
	public static String[] splitString(String string, int len) {
		int x = string.length() / len;
		int y = string.length() % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		String[] strings = new String[x + z];
		String str = "";
		for (int i = 0; i < x + z; i++) {
			if (i == x + z - 1 && y != 0) {
				str = string.substring(i * len, i * len + y);
			} else {
				str = string.substring(i * len, i * len + len);
			}
			strings[i] = str;
		}
		return strings;
	}

	/**
	 * 拆分数组
	 */
	public static byte[][] splitArray(byte[] data, int len) {
		int x = data.length / len;
		int y = data.length % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		byte[][] arrays = new byte[x + z][];
		byte[] arr;
		for (int i = 0; i < x + z; i++) {
			arr = new byte[len];
			if (i == x + z - 1 && y != 0) {
				System.arraycopy(data, i * len, arr, 0, y);
			} else {
				System.arraycopy(data, i * len, arr, 0, len);
			}
			arrays[i] = arr;
		}
		return arrays;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		HashMap<String, Object> map = RSAUtil.getKeys();
//		// 生成公钥和私钥
//		RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
//		RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
//		System.out.println("publicKey="+publicKey);
//		System.out.println("privateKey="+privateKey);
//		// 模
//		String modulus = publicKey.getModulus().toString();
//		System.out.println("modulus="+modulus);
//		// 公钥指数
//		String public_exponent = publicKey.getPublicExponent().toString();
//		System.out.println("公钥指数＝"+public_exponent);
//		// 私钥指数
//		String private_exponent = privateKey.getPrivateExponent().toString();
//		System.out.println("私钥指数＝"+private_exponent);
//		// 明文
//		String ming = "冬天啦了";
//		// 使用模和指数生成公钥和私钥
//		RSAPublicKey pubKey = RSAUtil.getPublicKey(modulus, public_exponent);
//		System.out.println("公钥＝"+pubKey);
//		RSAPrivateKey priKey = RSAUtil.getPrivateKey(modulus, private_exponent);
//		System.out.println("密钥＝"+priKey);
//		// 加密后的密文
//		String mi = RSAUtil.encryptByPublicKey(ming, pubKey);
//		System.err.println(mi);
//		// 解密后的明文
//		ming = RSAUtil.decryptByPrivateKey(mi, priKey);
//		System.err.println(ming);
		
		
		String mo = "103443868297555251495064875564430934194751973248039400988788637684978348762453697576293642187769625083272836672418809825496475912039962880238677432956668381734455386838499614805413804883074742801388546506182823395631064067558720167685371929005111993004806321210083852249878871409575146065475657718304094209849";
		String privat= "25915667819400389716623747515686764228904489830689064124187782714280402028568864654380142310573305856207538673351381445654548469037520935516260777893640490335669644679905175538313678675538403184040784375240509243451889626484121005289911271440731138063124496316994765185965321655590355765790321475461281256577";
//		String ss = "AE9C62063C0AB4E0AA8682B17DEFDE856F8D7A8F088E33AB9D7CF290DEE1C01BC0C47A756A488914DC2F2626E6CFFC3544F825294093B5773254FE855A3763A6D98CE5D9799B7406EDA8B0D03AC06271C1862508D75495B1F6737FA117D9814212217318F4BE9C143A118F484D5D97B566629954C3773CE43176FD3057343101";
		String ss = "1573B1D17C7B35D2F91C213A513D8BF38DEC156D779A1E1C31EAECDC7E0DEF090E6970AED76DD1716E2B876AAF0CFBF0AD9273528D58FFAAC68D8E964EF00DFB5515E29E9E18891E81C31EE43A003001F95A52C181ADEA0CBAF039EECF2A5C87CB968B09C8129F23F208AB337A78D364E4FC8B54721BF2B1F605266CD01D96CB";
		//		//解密
		RSAPrivateKey pk = RSAUtil.getPrivateKey(mo, privat);
		System.out.println(RSAUtil.decryptByPrivateKey(ss, pk));
	
//		String str = "{transactionNo:351501,userId:08136331,unitNo:34010100770002,unitName:安徽省交通厅,payamt:9.00,flag:0,feeType:08,androidId:3c7a44aa7948b073,mid:001,detail:330ml可口可乐 500ml营养快线500ml激活,key:1234567890123456}";
////		String str = "1234567890123456";
//		RSAPublicKey pubKey = RSAUtil.getPublicKey(mo, "65537");
//		System.out.println(RSAUtil.encryptByPublicKey(str, pubKey));
		
	}
	
	
	
}
