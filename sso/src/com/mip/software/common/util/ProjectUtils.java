package com.mip.software.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


public class ProjectUtils {
	
	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	private static final long ONE_MONTH = 2592000;
	private static final long ONE_YEAR = 31104000;
	
	public static Calendar calendar = Calendar.getInstance();
	
	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyy-mm-dd hh:mm:ss
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
	
	
	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyyMMddhhmmss
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getDateString() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
	
	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyyMMddhhmmssSSS
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getDateStringSss() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
	
	
	/**
	 * 返回自定义格式的当前日期时间字符串
	 * 
	 * @param format
	 *            格式规则
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime(String format) {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}

	/**
	 * 返回当前字符串型日期
	 * 
	 * @return String 返回的字符串型日期
	 */
	public static String getCurDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = simpledateformat.format(calendar.getTime());
		return strDate;
	}
	
	/**
	 * 将字符串型日期转换为日期型
	 * 
	 * @param strDate
	 *            字符串型日期
	 * @param srcDateFormat
	 *            源日期格式
	 * @param dstDateFormat
	 *            目标日期格式
	 * @return Date 返回的util.Date型日期
	 */
	public static Date stringToDate(String strDate, String srcDateFormat, String dstDateFormat) {
		Date rtDate = null;
		Date tmpDate = (new SimpleDateFormat(srcDateFormat)).parse(strDate, new ParsePosition(0));
		String tmpString = null;
		if (tmpDate != null)
			tmpString = (new SimpleDateFormat(dstDateFormat)).format(tmpDate);
		if (tmpString != null)
			rtDate = (new SimpleDateFormat(dstDateFormat)).parse(tmpString, new ParsePosition(0));
		return rtDate;
	}
	
	/**
	 * 得到明天的日期
	 * @return
	 */
	public static String getTomorrow(){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar afterTime = Calendar.getInstance(); 
		afterTime.add(Calendar.DATE, 1); // 
	    Date afterDate = (Date) afterTime.getTime();
	    return f.format(afterDate);
	}
	
	/**
	 * 日期减多少天(subtrahenden为负数)
	 * 日期加多少天(subtrahenden为正数)
	 * @return
	 */
	public static String getYesterday(String date,int subtrahenden){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar afterTime=Calendar.getInstance();
		try {
			afterTime.setTime(f.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		afterTime.add(Calendar.DATE,subtrahenden);
	    Date afterDate = (Date) afterTime.getTime();
	    return f.format(afterDate);
	}
	
	/**
	 * 
	 * @param longidstr long类型的ID集合如这样的结构(1,2,5,6)
	 * @param format  传入要切割的分隔符 
	 * @return 返回mybatis 删除所需要的list<long>
	 */
	public static List<Integer> getIntListList(String ids,String format){
		String[] strings=ids.split(format);
		List<Integer> list=new ArrayList<Integer>();
		for (String string : strings) {
			if(!StringUtils.isBlank(string)){
				list.add(Integer.parseInt(string));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param longidstr long类型的ID集合如这样的结构(1,2,5,6)
	 * @param format  传入要切割的分隔符 
	 * @return 返回mybatis 删除所需要的list<long>
	 */
	public static List<String> getStringList(String longidstr,String format){
		String[] strings=longidstr.split(format);
		List<String> list=new ArrayList<String>();
		for (String string : strings) {
			if(!StringUtils.isBlank(string)){
				list.add(string);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param longidstr long类型的ID集合如这样的结构(1,2,5,6)
	 * @param format  传入要切割的分隔符 
	 * @return 返回mybatis 删除所需要的list<long>
	 */
	public static List<Long> getLongList(String longidstr,String format){
		String[] strings=StringUtils.split(longidstr,format);
		List<Long> list=new ArrayList<Long>();
		for (String string : strings) {
			if(!StringUtils.isBlank(string)){
				list.add(Long.parseLong(string));
			}
		}
		return list;
	}

	/**
	 * 将传入的身份证号码进行校验，并返回一个对应的18位身份证
	 * 
	 * @param personIDCode
	 *            身份证号码
	 * @return String 十八位身份证号码
	 * @throws 无效的身份证号
	 */
	public static String getFixedPersonIDCode(String personIDCode) throws Exception {
		if (personIDCode == null)
			throw new Exception("输入的身份证号无效，请检查");

		if (personIDCode.length() == 18) {
			if (isIdentity(personIDCode))
				return personIDCode;
			else
				throw new Exception("输入的身份证号无效，请检查");
		} else if (personIDCode.length() == 15)
			return fixPersonIDCodeWithCheck(personIDCode);
		else
			throw new Exception("输入的身份证号无效，请检查");
	}

	/**
	 * 修补15位居民身份证号码为18位，并校验15位身份证有效性
	 * 
	 * @param personIDCode
	 *            十五位身份证号码
	 * @return String 十八位身份证号码
	 * @throws 无效的身份证号
	 */
	public static String fixPersonIDCodeWithCheck(String personIDCode) throws Exception {
		if (personIDCode == null || personIDCode.trim().length() != 15)
			throw new Exception("输入的身份证号不足15位，请检查");

		if (!isIdentity(personIDCode))
			throw new Exception("输入的身份证号无效，请检查");

		return fixPersonIDCodeWithoutCheck(personIDCode);
	}

	/**
	 * 修补15位居民身份证号码为18位，不校验身份证有效性
	 * 
	 * @param personIDCode
	 *            十五位身份证号码
	 * @return 十八位身份证号码
	 * @throws 身份证号参数不是15位
	 */
	public static String fixPersonIDCodeWithoutCheck(String personIDCode) throws Exception {
		if (personIDCode == null || personIDCode.trim().length() != 15)
			throw new Exception("输入的身份证号不足15位，请检查");

		String id17 = personIDCode.substring(0, 6) + "19" + personIDCode.substring(6, 15); // 15位身份证补'19'

		char[] code = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // 11个校验码字符
		int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 }; // 18个加权因子
		int[] idcd = new int[18];
		int sum; // 根据公式 ∑(ai×Wi) 计算
		int remainder; // 第18位校验码
		for (int i = 0; i < 17; i++) {
			idcd[i] = Integer.parseInt(id17.substring(i, i + 1));
		}
		sum = 0;
		for (int i = 0; i < 17; i++) {
			sum = sum + idcd[i] * factor[i];
		}
		remainder = sum % 11;
		String lastCheckBit = String.valueOf(code[remainder]);
		return id17 + lastCheckBit;
	}

	/**
	 * 判断是否是有效的18位或15位居民身份证号码
	 * 
	 * @param identity
	 *            18位或15位居民身份证号码
	 * @return 是否为有效的身份证号码
	 */
	public static boolean isIdentity(String identity) {
		if (identity == null)
			return false;
		if (identity.length() == 18 || identity.length() == 15) {
			String id15 = null;
			if (identity.length() == 18)
				id15 = identity.substring(0, 6) + identity.substring(8, 17);
			else
				id15 = identity;
			try {
				Long.parseLong(id15); // 校验是否为数字字符串

				String birthday = "19" + id15.substring(6, 12);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				sdf.parse(birthday); // 校验出生日期
				if (identity.length() == 18 && !fixPersonIDCodeWithoutCheck(id15).equals(identity))
					return false; // 校验18位身份证
			} catch (Exception e) {
				return false;
			}
			return true;
		} else
			return false;
	}

	/**
	 * 从身份证号中获取出生日期，身份证号可以为15位或18位
	 * 
	 * @param identity
	 *            身份证号
	 * @return 出生日期
	 * @throws 身份证号出生日期段有误
	 */
	public static Timestamp getBirthdayFromPersonIDCode(String identity) throws Exception {
		String id = getFixedPersonIDCode(identity);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Timestamp birthday = new Timestamp(sdf.parse(id.substring(6, 14)).getTime());
			return birthday;
		} catch (Exception e) {
			throw new Exception("不是有效的身份证号，请检查");
		}
	}

	/**
	 * 从身份证号获取性别
	 * 
	 * @param identity
	 *            身份证号
	 * @return 性别代码 2:标示男;1标示女
	 * @throws Exception
	 *             无效的身份证号码
	 */
	public static String getGenderFromPersonIDCode(String identity) throws Exception {
		String id = getFixedPersonIDCode(identity);
		char sex = id.charAt(16);
		return sex % 2 == 0 ? "2" : "1";
	}
	
	/**
	 * 从身份证号获取年龄
	 * @param IDCardNum 身份证
	 * @return 年龄
	 */
	public static int getAge(String IDCardNum) {
		int year, month, day, idLength = IDCardNum.length();
		Calendar cal1 = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		if (idLength == 18) {
			year = Integer.parseInt(IDCardNum.substring(6, 10));
			month = Integer.parseInt(IDCardNum.substring(10, 12));
			day = Integer.parseInt(IDCardNum.substring(12, 14));
		} else if (idLength == 15) {
			year = Integer.parseInt(IDCardNum.substring(6, 8)) + 1900;
			month = Integer.parseInt(IDCardNum.substring(8, 10));
			day = Integer.parseInt(IDCardNum.substring(10, 12));
		} else {
			return -1;
		}
		cal1.set(year, month, day);
		return getYearDiff(today, cal1);
	} 
	
	@SuppressWarnings("static-access")
	public static   int   getYearDiff(Calendar   cal,   Calendar   cal1){ 
		int   m   =   (cal.get(cal.MONTH))   -   (cal1.get(cal1.MONTH)); 
		int   y   =   (cal.get(cal.YEAR))   -   (cal1.get(cal1.YEAR)); 
		return   (y   *   12   +   m)/12; 
	} 
	
	public static String getIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
	    if(ip==null||ip.length()==0|| "unknown".equalsIgnoreCase(ip)){
	    	ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    	ip = request.getHeader("WL-Proxy-Client-IP");
		}
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
	    if(!StringUtils.isEmpty(ip)&&ip.length()>15){
	    	if(ip.indexOf(",")>0){  
	    		ip = ip.substring(0,ip.indexOf(","));  
            } 
	    }
	    return ip;
	}
    
	 /**
	  * 生成任意位数随机数  
	  * @param len(位数) 
	  * @return 随机数
	  */  
     public static String validateCode(int len) {   
         int count = 0;   
         char str[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };   
         StringBuffer pwd = new StringBuffer("");   
         Random r = new Random();   
         while (count < len) {   
             int i = Math.abs(r.nextInt(10));   
             if (i >= 0 && i < str.length) {   
                 pwd.append(str[i]);   
                 count++;   
             }   
         }   
         return pwd.toString();   
     }
     
    public static boolean isChinese(char a) {
    		int v = (int)a;
    		return (v >= 19968 && v <= 171941);
    }
    
    /**
     * Java中判断输入的字符串是否是汉字
     * @param s
     * @return
     */
    public static boolean chontainsChinese(String s) {
    	if (null == s || "".equals(s.trim())) return false;
    	for (int i = 0; i < s.length(); i++) {
    		if (isChinese(s.charAt(i))) return true;
    	}
    	return false;
    }
	
	/**
	 * 判断是否数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	} 
	
	/**
	 * 返回流水号20位 yyyyMMddHHmmssSSS+随机三位
	 */
	public static String getNumCode() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr+game(3);
	}
	
	/**
     * 获取count个随机数
     * @param count 随机数个数
     * @return
     */
    public static String game(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }
    
	/**
     * 
     * @return yyyy-mm-dd
     *  2012-12-25
     */
    public static String getDate() {
        return getYear() + "-" + getMonth() + "-" + getDay();
    }
 
    /**
     * @param format
     * @return 
     * yyyy年MM月dd HH:mm 
     * MM-dd HH:mm 2012-12-25
     * 
     */
    public static String getDate(String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        return simple.format(calendar.getTime());
    }
 
    /**
     * 
     * @return yyyy-MM-dd HH:mm 
     * 2012-12-29 23:47
     */
    public static String getDateAndMinute() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simple.format(calendar.getTime());
    }
 
    /**
     * 
     * @return
     *  yyyy-MM-dd HH:mm:ss 
     *  2012-12-29 23:47:36
     */
    public static String getFullDate() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simple.format(calendar.getTime());
    }
 
    /**
     * 距离今天多久
     * @param date
     * @return 
     * 
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
 
        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟前";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)
                    + "分钟前";
        else if (ago <= ONE_DAY * 2)
            return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        else if (ago <= ONE_DAY * 3)
            return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月" + day + "天前"
                    + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
            return year + "年前" + month + "月" + calendar.get(Calendar.DATE)
                    + "日";
        }
 
    }
 
    /**
     * 距离截止日期还有多长时间
     * 
     * @param date
     * @return
     */
    public static String fromDeadline(Date date) {
        long deadline = date.getTime() / 1000;
        long now = (new Date().getTime()) / 1000;
        long remain = deadline - now;
        if (remain <= ONE_HOUR)
            return "只剩下" + remain / ONE_MINUTE + "分钟";
        else if (remain <= ONE_DAY)
            return "只剩下" + remain / ONE_HOUR + "小时"
                    + (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
        else {
            long day = remain / ONE_DAY;
            long hour = remain % ONE_DAY / ONE_HOUR;
            long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
        }
 
    }
 
    /**
     * 距离今天的绝对时间
     * 
     * @param date
     * @return
     */
    public static String toToday(Date date) {
        long time = date.getTime() / 1000;
        long now = (new Date().getTime()) / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟";
        else if (ago <= ONE_DAY * 2)
            return "昨天" + (ago - ONE_DAY) / ONE_HOUR + "点" + (ago - ONE_DAY)
                    % ONE_HOUR / ONE_MINUTE + "分";
        else if (ago <= ONE_DAY * 3) {
            long hour = ago - ONE_DAY * 2;
            return "前天" + hour / ONE_HOUR + "点" + hour % ONE_HOUR / ONE_MINUTE
                    + "分";
        } else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            long hour = ago % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return day + "天前" + hour + "点" + minute + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return month + "个月" + day + "天" + hour + "点" + minute + "分前";
        } else {
            long year = ago / ONE_YEAR;
            long month = ago % ONE_YEAR / ONE_MONTH;
            long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;
            return year + "年前" + month + "月" + day + "天";
        }
 
    }
 
    public static String getYear() {
        return calendar.get(Calendar.YEAR) + "";
    }
 
    public static String getMonth() {
        int month = calendar.get(Calendar.MONTH) + 1;
        return month + "";
    }
 
    public static String getDay() {
        return calendar.get(Calendar.DATE) + "";
    }
 
    public static String get24Hour() {
        return calendar.get(Calendar.HOUR_OF_DAY) + "";
    }
 
    public static String getMinute() {
        return calendar.get(Calendar.MINUTE) + "";
    }
 
    public static String getSecond() {
        return calendar.get(Calendar.SECOND) + "";
    }
	
    public static void Copy(Object source, Object dest) throws Exception {
        // 获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try {
            for (int i = 0; i < sourceProperty.length; i++) {
                for (int j = 0; j < destProperty.length; j++) {
                    if (sourceProperty[i].getName().equals(destProperty[j].getName())) {
                        destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("属性复制失败:" + e.getMessage());
        }
    }
    
    public static String getNUll(Object object){
    	if(null!=object){
    		return object.toString();
    	}else{
    		return "";
    	}
    }
    
    
    // 默认运算精度
 	private static int DEF_SCALE = 2;
    /**
	 * 提供(相对)精确的加法运算。
	 * 
	 * @param num1 被加数
	 * @param num2 加数
	 * @return 两个参数的和
	 */
	public static final BigDecimal add(BigDecimal num1, BigDecimal num2) {
		if(null==num1){
			return num2;
		}
		if(null==num2){
			return num1;
		}
		BigDecimal result = num1.add(num2);
		return result.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_UP);
	}
   
	/**
	 * 提供(相对)精确的减法运算。
	 * 
	 * @param num1 减数
	 * @param num2 被减数
	 * @return 
	 */
	public static final BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
		if(null==num1){
			return num2;
		}
		if(null==num2){
			return num1;
		}
		BigDecimal result = num1.subtract(num2);
		return result.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_UP);
	}
	
	
	/**
	 * 左补位，右对齐
	 * 
	 * @param oriStr
	 *            原字符串
	 * @param len
	 *            目标字符串长度
	 * @param alexin
	 *            补位字符
	 * @return 目标字符串
	 */
	public static String padLeft(String oriStr, int len, char alexin) {
		int strlen = oriStr.length();
		String str="";
		if (strlen < len) {
			for (int i = 0; i < len - strlen; i++) {
				str = str + alexin;
			}
		}
		str = oriStr + str;
		return str;
	}
	
	
	/**
	 * 右补位，左对齐
	 * 
	 * @param oriStr
	 *            原字符串
	 * @param len
	 *            目标字符串长度
	 * @param alexin
	 *            补位字符
	 * @return 目标字符串
	 */
	public static String padRight(String oriStr, int len, char alexin) {
		int val=(Integer.valueOf(oriStr)+1);
		int strlen = String.valueOf(val).length();
		String str="";
		if (strlen < len) {
			for (int i = 0; i < len - strlen; i++) {
				str = str + alexin;
			}
		}
		str = str + String.valueOf(val);
		return str;
	}
	 
	 
    public static void main(String[] args) {
    	System.out.println("----"+("JH"+89).length());
    	System.out.println("-----"+padRight("100",3,'0'));
		System.out.println("-------"+("JH"+89)+padRight("90001",4,'0'));
	}
}
