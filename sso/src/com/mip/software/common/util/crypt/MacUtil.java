/*
 * @(#)MacUtil.java      1.0   2013-3-28
 *
 * Copyright (c) 2009 Sunyard System Engineering Co., Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of 
 * Sunyard System Engineering Co., Ltd. ("Confidential Information").  
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of the license agreement you entered 
 * into with Sunyard.
 */
 /* Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 
 */ 

package com.mip.software.common.util.crypt;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 
 *
 * @version 1.0   2013-3-28
 * @author openzhang
 */
public class MacUtil {
	
	private static final int[][] s1 = 
		{
			{14,4, 13,1, 2, 15,11,8, 3, 10,6, 12,5, 9, 0, 7},
			{0, 15,7, 4, 14,2, 13,1, 10,6, 12,11,9, 5, 3, 8},
			{4, 1, 14,8, 13,6, 2, 11,15,12,9, 7, 3, 10,5, 0},
			{15,12,8, 2, 4, 9, 1, 7, 5, 11,3, 14,10,0, 6, 13}
		 };

	private static final int[][] s2 = 
		{
			{15,1, 8, 14,6, 11,3, 4, 9, 7, 2, 13,12,0, 5, 10},
			{3, 13,4, 7, 15,2, 8, 14,12,0, 1, 10,6, 9, 11,5},
			{0, 14,7, 11,10,4, 13,1, 5, 8, 12,6, 9, 3, 2, 15},
			{13,8, 10,1, 3, 15,4, 2, 11,6, 7, 12,0, 5, 14,9}
		};
	private static final int[][] s3 = 
		{
			{10,0, 9, 14,6, 3, 15,5, 1, 13,12,7, 11,4, 2, 8},
		 	{13,7, 0, 9, 3, 4, 6, 10,2, 8, 5, 14,12,11,15,1},
		    {13,6, 4, 9, 8, 15,3, 0, 11,1, 2, 12,5, 10,14,7},
		    {1, 10,13,0, 6, 9, 8, 7, 4, 15,14,3, 11,5, 2, 12}
		};
	private static final int[][] s4 = 
		{
			{7, 13,14,3, 0, 6, 9, 10,1, 2, 8, 5, 11,12,4, 15},
		    {13,8, 11,5, 6, 15,0, 3, 4, 7, 2, 12,1, 10,14,9},
		    {10,6, 9, 0, 12,11,7, 13,15,1, 3, 14,5, 2, 8, 4},
		    {3, 15,0, 6, 10,1, 13,8, 9, 4, 5, 11,12,7, 2, 14}
		};
	private static final int[][] s5 = 
		{
			{2, 12,4, 1, 7, 10,11,6, 8, 5, 3, 15,13,0, 14,9},
		    {14,11,2, 12,4, 7, 13,1, 5, 0, 15,10,3, 9, 8, 6},
		    {4, 2, 1, 11,10,13,7, 8, 15,9, 12,5, 6, 3, 0, 14},
		    {11,8, 12,7, 1, 14,2, 13,6, 15,0, 9, 10,4, 5, 3}
		};

	private static final int[][] s6 = 
		{
			{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
		    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
		    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
		    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
		};
	private static final int[][] s7 =
		{
			{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
		    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
		    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
		    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
		};

	private static final int[][] s8 = 
		{
			{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
			{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
			{7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
			{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
		};

	private static final int[] ip = 
		{58, 50, 42, 34, 26, 18, 10, 2,
		60, 52, 44, 36, 28, 20, 12, 4,
		62, 54, 46, 38, 30, 22, 14, 6,
		64, 56, 48, 40, 32, 24, 16, 8,
		57, 49, 41, 33, 25, 17, 9, 1,
		59, 51, 43, 35, 27, 19, 11, 3,
		61, 53, 45, 37, 29, 21, 13, 5,
		63, 55, 47, 39, 31, 23, 15, 7};

	private static final int[] _ip = 
		{40, 8, 48, 16, 56, 24, 64, 32,
		39, 7, 47, 15, 55, 23, 63, 31,
		38, 6, 46, 14, 54, 22, 62, 30,
		37, 5, 45, 13, 53, 21, 61, 29,
		36, 4, 44, 12, 52, 20, 60, 28,
		35, 3, 43, 11, 51, 19, 59, 27,
		34, 2, 42, 10, 50, 18, 58, 26,
		33, 1, 41, 9, 49, 17, 57, 25};

	private static final int[] LS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1}; 
	private static int[][] subKey = new int[16][48];
	private static int HEX = 0;

	private static int ASC = 1;
	
	
	public static int getIntByChar(char ch) throws Exception
	{
		char t = Character.toUpperCase(ch);
		int i = 0;
		switch(t){
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				i = Integer.parseInt(Character.toString(t));
				break;
			case 'A':
				i = 10;
				break;
			case 'B':
				i = 11;
				break;
			case 'C':
				i = 12;
				break;
			case 'D':
				i = 13;
				break;
			case 'E':
				i = 14;
				break;
			case 'F':
				i = 15;
				break;
			default:
				throw new Exception("getIntByChar was wrong");
		}
		return i;
	}

	public static int[] string2Binary(String source)
	{
		int len = source.length();
		int[] dest = new int[len*4];
		char[] arr = source.toCharArray();
		for(int i=0; i<len; i++)
		{
			int t = 0;
			try {
				t = getIntByChar(arr[i]);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
	
			String[] str = Integer.toBinaryString(t).split("");
			int k = i*4 + 3;
			for(int j=str.length-1; j>0; j--)
			{
				dest[k] = Integer.parseInt(str[j]);
				k--;
			}
		}
		return dest;
	}


	public static int getXY(int x,int y)
	{
		int temp = x;
		if(y == 0) x = 1;
		for(int i=2; i<=y; i++)
		{
			x *= temp;  
		}
		return x;
	}

	public static String binary2Hex(String s)
	{
		int len = s.length();
		int result = 0;
		int k = 0;
		if(len > 4)
			return null;
		for(int i=len; i>0; i--)
		{
			result += Integer.parseInt(s.substring(i-1, i))*getXY(2,k);
			k++;
		}
		switch(result){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				return ""+result;
			case 10:
				return "A";
			case 11:
				return "B";
			case 12:
				return "C";
			case 13:
				return "D";
			case 14:
				return "E";
			case 15:
				return "F";
			default :
				return null;
		}
	}

	public static String int2Hex(int i)
	{
		switch(i){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				return ""+i;
			case 10:
				return "A";
			case 11:
				return "B";
			case 12:
				return "C";
			case 13:
				return "D";
			case 14:
				return "E";
			case 15:
				return "F";
			default :
				return null;
		}
	}

	
	public static String binary2ASC(String s)
	{
		String str = "";
		int ii = 0;
		int len = s.length();
		if(len%4 != 0)
		{
			while(ii<4-len%4)
			{
				s = "0" + s;
			}
		}
		for(int i=0; i<len/4; i++)
		{
			str += binary2Hex(s.substring(i*4, i*4+4));
		}
		return str;
	}

	public static int[] changeIP(int[] source)
	{
		int[] dest = new int[64];
		for(int i=0; i<64; i++)
		{
			dest[i] = source[ip[i]-1];
		}
		return dest;
	}


	public static int[] changeInverseIP(int[] source)
	{
		int[] dest = new int[64];
		for(int i=0; i<64; i++)
		{
			dest[i] = source[_ip[i]-1];
		}
		return dest;
	}

		 
	public static int[] expend(int[] source)
	{
		int[] ret = new int[48];
		int[] temp = {32, 1, 2, 3, 4, 5,
		4, 5, 6, 7, 8, 9,
		8, 9, 10, 11, 12, 13,
		12, 13, 14, 15, 16, 17,
		16, 17, 18, 19, 20, 21,
		20, 21, 22, 23, 24, 25,
		24, 25, 26, 27, 28, 29,
		28, 29, 30, 31, 32, 1};
		for(int i=0; i<48; i++)
		{
			ret[i] = source[temp[i]-1];
		}
		return ret;
	} 


	public static int[] press(int[] source)
	{
		int[] ret = new int[32];
		int[][] temp = new int[8][6];
		int[][][] s = {s1,s2,s3,s4,s5,s6,s7,s8};
		StringBuffer str = new StringBuffer();
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<6; j++)
			{
				temp[i][j] = source[i*6+j];
			}
		}

		for(int i=0; i<8; i++)
		{
			int x = temp[i][0]*2 + temp[i][5];
			int y = temp[i][1]*8 + temp[i][2]*4 + temp[i][3]*2 + temp[i][4];
			int val = s[i][x][y];
			String ch = int2Hex(val);
			str.append(ch);
		}

		ret = string2Binary(str.toString());
		ret = dataP(ret);
		
		return ret;
	}

	public static int[] dataP(int[] source)
	{
		int[] dest = new int[32];
		int[] temp = 
		{16, 7, 20, 21,
		 29, 12, 28, 17,
		 1, 15, 23, 26,
		 5, 18, 31, 10,
		 2, 8, 24, 14,
		 32, 27, 3, 9,
		 19, 13, 30, 6,
		 22, 11, 4, 25};

		int len = source.length;
		for(int i=0; i<len; i++)
		{
			dest[i] = source[temp[i]-1];
		}

		return dest;
	}

	public static int[] f(int[] R,int[] K)
	{
		int[] dest = new int[32];
		int[] temp = new int[48]; 
		int[] expendR = expend(R);

		temp = diffOr(expendR, K);
		dest = press(temp);

		return dest;
	}


	public static int[] diffOr(int[] source1,int[] source2)
	{
		int len = source1.length;
		int[] dest = new int[len];

		for(int i=0; i<len; i++)
		{
			dest[i] = source1[i] ^ source2[i];
		}

		return dest;
	}

	public static String encryption(String D, String K)
	{
		String str = "";
		int[] temp = new int[64];
		int[] data = string2Binary(D);

		data = changeIP(data);
		int[][] left = new int[17][32];
		int[][] right = new int[17][32];

		for(int j=0; j<32; j++)
		{
			left[0][j] = data[j];
			right[0][j] = data[j+32];
		}

		setKey(K);
		for(int i=1; i<17; i++)
		{
			int[] key = subKey[i-1];
			left[i] = right[i-1];
			int[] fTemp = f(right[i-1],key);
			right[i] = diffOr(left[i-1],fTemp);
		}

		for(int i=0; i<32; i++)
		{
			temp[i] = right[16][i];
			temp[32+i] = left[16][i];
		}

		temp = changeInverseIP(temp);
		str = binary2ASC(intArr2Str(temp));

		return str;
	}


	public static String discryption(String source,String key){
		String str = "";
		int[] data = string2Binary(source);
		data = changeIP(data);
		int[] left = new int[32];
		int[] right = new int[32];
		int[] tmp = new int[32];
		for(int j=0; j<32; j++)
		{
			left[j] = data[j];
			right[j] = data[j+32];
		}

		setKey(key);
		for(int i=16; i>0; i--)
		{
			int[] sKey = subKey[i-1];
			tmp = left;
			left = right;
			int[] fTemp = f(right,sKey);
			right = diffOr(tmp,fTemp);
		}

		for(int i=0; i<32; i++)
		{
			data[i] = right[i];
			data[32+i] = left[i];
		}

		data = changeInverseIP(data);
		for(int i=0; i<data.length; i++)
		{
			str += data[i];
		}

		str = binary2ASC(str);
		
		return str;
	}

	
	public static String DES_1(String source,String key,int type)
	{
		if(source.length() != 16 || key.length() != 16)
			return null;
		if(type==0)
		{
			return encryption(source, key);
		}
		if(type==1)
		{
			return discryption(source, key);
		}

		return null;
	}


	
	public static String DES_3(String source,String key,int type)
	{
		if(key.length() != 32 || source.length() != 16)
			return null;
		String temp = null;
		String K1 = key.substring(0, key.length()/2);
		String K2 = key.substring(key.length()/2);

		if(type==0)
		{
			temp = encryption(source, K1);
			temp = discryption(temp, K2);
	
			return encryption(temp, K1);
		}

		if(type==1)
		{
			temp = discryption(source, K1);
			temp = encryption(temp, K2);
			return discryption(temp, K1);
		}

		return null;
	}
	
	
	public static String DES_3_32(String source,String key,int type)
	{
		if(key.length() != 32 || source.length() != 32)
			return null;
		String srcfirst = source.substring(0,16);
		String srcsecond = source.substring(16);
		
		return DES_3(srcfirst,key,type) + DES_3(srcsecond,key,type);
	}

	public static int[] keyPC_1(int[] source)
	{
		int[] dest = new int[56];
		int[] temp = 
		{57, 49, 41, 33, 25, 17, 9,
		 1, 58, 50, 42, 34, 26, 18,
		 10, 2, 59, 51, 43, 35, 27,
		 19, 11, 3, 60, 52, 44, 36,
		 63, 55, 47, 39, 31, 23, 15,
		 7, 62, 54, 46, 38, 30, 22,
		 14, 6, 61, 53, 45, 37, 29,
		 21, 13, 5, 28, 20, 12, 4};

		for(int i=0; i<56; i++)
		{
			dest[i] = source[temp[i]-1];
		}
		
		return dest;
	}

	public static int[] keyLeftMove(int[] source, int i){
		int temp = 0;
		int len = source.length;
		int ls = LS[i];
		for(int k=0; k<ls; k++)
		{
			temp = source[0];
			for(int j=0; j<len-1; j++)
			{
				source[j] = source[j+1];
			}
			source[len-1] = temp;
		}
		return source;
	}

	public static int[] keyPC_2(int[] source)
	{
		int[] dest = new int[48];
		int[] temp = 
		{14, 17, 11, 24, 1, 5,
		 3, 28, 15, 6, 21, 10,
		 23, 19, 12, 4, 26, 8,
		 16, 7, 27, 20, 13, 2,
		 41, 52, 31, 37, 47, 55,
		 30, 40, 51, 45, 33, 48,
		 44, 49, 39, 56, 34, 53,
		 46, 42, 50, 36, 29, 32};

		for(int i=0; i<48; i++)
		{
			dest[i] = source[temp[i]-1];
		}

		return dest;
	}

	public static void setKey(String source)
	{
		if(subKey.length > 0)
			subKey = new int[16][48];

		int[] temp = string2Binary(source);
		int[] left = new int[28];
		int[] right = new int[28];
		int[] temp1 = new int[56];
		temp1 = keyPC_1(temp);

		for(int i=0; i<28; i++)
		{
			left[i] = temp1[i];
			right[i] = temp1[i+28];
		}

		for(int i=0; i<16; i++)
		{
			left = keyLeftMove(left, LS[i]);
			right = keyLeftMove(right, LS[i]);
	
			for(int j=0; j<28; j++)
			{
				temp1[j] = left[j];
				temp1[j+28] = right[j];
			}
			subKey[i] = keyPC_2(temp1);
		}
	}

	
	public static String ASC_2_HEX(String asc)
	{
//		StringBuffer hex = new StringBuffer();
//		try 
//		{
//			byte[] bs = asc.getBytes("utf-8");
//			for(byte b : bs)
//			{
//				hex.append(Integer.toHexString(new Byte(b).intValue()));
//			}
//		} 
//		catch (UnsupportedEncodingException e) 
//		{
//			e.printStackTrace();
//		}
//		return hex.toString();
		
		
		 String ret = "";
		  
		try {
			byte[] bs; bs = asc.getBytes("gbk");
			 for (int i = 0; i < bs.length; i++) {
				   String hex = Integer.toHexString(bs[ i ] & 0xFF);
				   if (hex.length() == 1) {
				    hex = '0' + hex;
				   }
				   ret += hex.toUpperCase();
				  }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		  return ret;
	}

	public static String HEX_2_ASC(String hex)
	{
		String asc = null;
		int len = hex.length();
		byte[] bs = new byte[len/2];
		for(int i=0; i<len/2; i++)
		{
			bs[i] = Byte.parseByte(hex.substring(i*2, i*2+2), 16);
		}
		try 
		{
			asc = new String(bs,"utf-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		
		return asc;
	}

	public static String xOr(String s1,String s2)
	{
		int[] iArr = diffOr(string2Binary(s1), string2Binary(s2));
		return binary2ASC(intArr2Str(iArr));
	}


	public static String intArr2Str(int[] arr)
	{
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<arr.length; i++)
		{
			sb.append(arr[i]);
		}

		return sb.toString();
	}

	public static String divData(String data,String key,int type){
		String left = null;
		String right = null;
		if(type == HEX)
		{
			left = key.substring(0, 16);
			right = key.substring(16,32);
		}

		if(type == ASC)
		{
			left = ASC_2_HEX(key.substring(0, 8));
			right = ASC_2_HEX(key.substring(8,16));
		}

		data = DES_1(data, left, 0);
		data = DES_1(data, right, 1);
		data = DES_1(data, left, 0);

		return data;
	}	

	public static String reverse(String source)
	{
		int[] data = string2Binary(source);
		int j = 0;
		for(int i : data)
		{
			data[j++] = 1 - i;
		}
		return binary2ASC(intArr2Str(data));
	}
	
	public static String getDPK(String issuerFlag,String appNo,String mpk)
	{
		StringBuffer issuerMPK = new StringBuffer();
		issuerMPK.append(divData(issuerFlag, mpk, 0));
		issuerMPK.append(divData(reverse(issuerFlag), mpk, 0));
		StringBuffer dpk = new StringBuffer();
		dpk.append(divData(appNo, issuerMPK.toString(), 0));
		dpk.append(divData(reverse(appNo), issuerMPK.toString(), 0));
		
		return dpk.toString();
	}
		
	public static String xOrString(String pan,String pin)
		{
			if(pan.length() != pin.length())
			{
				new Exception("ss").printStackTrace();
				return null;
			}
			
			byte [] bytepan = ByteUtil.getHexByte(pan);
			byte [] bytepin = ByteUtil.getHexByte(pin);
			
			byte [] result = new byte[bytepan.length];
			
			for(int i = 0;i < result.length;i++)
			{
				result [i] = (byte)(bytepan[i] ^ bytepin[i]);
			}
			
			return ByteUtil.getHexStr(result);
		}
		
		
		public static String createPwd(String cardNo,String pwd,String key)
		{
			String last6 = cardNo.substring(cardNo.length() - 13,cardNo.length() - 1);
			String first2 = "0000";
			String pan = first2 + last6;
			
			String pin = "06" + pwd + "FFFFFFFF";
			
			String result = xOrString(pan,pin);
			
			String ret = DES_3(result,key,0);
			
			return ret;
		}
		
		
		public static String TMAC(String key,String vector,String data)
		
		{
			
			data = ASC_2_HEX(data);
			System.out.println(">>>>>>>>>>>>>>>>>"+data);
			if(vector == null || vector.length() != 16)
				vector = "0000000000000000";
			
			StringBuffer sb = new StringBuffer(data);
			int mod = data.length()%16;
			if(mod != 0)
			{
				for(int i = 0;i < 16 - mod;i++)
				{
					sb.append("0");
				}
			}
			
			String operator = sb.toString();
			
			int count = operator.length()/16;
			String [] blocks = new String[count];
			
			for(int i = 0;i < count;i++)
			{
				blocks[i] = operator.substring(i*16, i*16 + 16);
				System.out.println("D[" + i + "]=" + blocks[i]);
			}
			
			
			for(int i = 0;i < count;i++)
			{
				String xor = xOrString(vector,blocks[i]);
				vector = DES_1(xor,key,0);
			}
			return vector;
		}
		
		
		public static String Mac_919(String key,String vector,String data)
		{
			if(key.length() != 32)
			{
				new Exception("key of ansix9.19 must be 32").printStackTrace();
				return null;
			}
			
			String left = key.substring(0, 16);
			String right = key.substring(16);
			
			String mac = TMAC(left,null,data);
			String result1 = DES_1(mac,right,1);
			String result2 = DES_1(result1,left,0);
			
			String ret = result2.substring(0,8);
			return ret;
		}
		
		
		public static String createRandom(int length)
		{
			StringBuffer sb = new StringBuffer("");
			Random random = new Random();
			for(int i = 0;i < length;i++)
			{
				int abs = Math.abs(random.nextInt()%10);
				sb.append("" + abs);
			}
			return sb.toString();
		}
		
		
		public static String zpkzakDecode(String codesrc,String key)
		{
			int len = codesrc.length();
			if(len != 96)
				return null;
			
			String zpkcode = codesrc.substring(0,32);
			String zpkcheck = codesrc.substring(32,48);
			String zakcode = codesrc.substring(48,80);
			String zakcheck = codesrc.substring(80);
			
			String zpk = DES_3_32(zpkcode,key,1);
			String zak = DES_3_32(zakcode,key,1);
			
			String checkresultzpk = DES_3("0000000000000000",zpk,0);
			String checkresultzak = DES_3("0000000000000000",zak,0);
			
			if(!zpkcheck.equals(checkresultzpk) || !zakcheck.equals(checkresultzak))
			{
				return null;
			}
			
			return zpk+zak;
		}
		
		public static void passwordDecode(String mainKey,String workKeyEnc,String cardNo,String pin)
		{
			String workkey = DES_3_32(workKeyEnc,mainKey,1);
			
			int len = cardNo.length();
			String pan = "0000" + cardNo.substring(len - 13, len - 1);
			
			String pinSource = DES_3(pin,workkey,1);
			
			String password = xOrString(pinSource,pan);
			
		
		}
		
		public static String TMAC(String passw){
			return TMAC("0123456789ABCDEF","0000000000000000",passw);
		}
		
		public static void main(String[] org) throws Exception{
			System.out.println(TMAC("0123456789ABCDEF","0000000000000000","111111"));
		}
}