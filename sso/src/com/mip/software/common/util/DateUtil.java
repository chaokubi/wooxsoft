package com.mip.software.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil
{
    private static final int[] dayArray = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static SimpleDateFormat sdf = new SimpleDateFormat();

    public static synchronized Calendar getCalendar()
    {
        return GregorianCalendar.getInstance();
    }
    
    /**
     @return String
     */
    public static synchronized String getDateMilliFormat()
    {
        Calendar cal = Calendar.getInstance();
        return getDateMilliFormat(cal);
    }

    /**
     @param cal
     @return String
     */
    public static synchronized String getDateMilliFormat(java.util.Calendar cal)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
        return getDateFormat(cal, pattern);
    }

    /**
     @param date
     @return String
     */
    public static synchronized String getDateMilliFormat(java.util.Date date)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
        return getDateFormat(date, pattern);
    }

    /**
     @param strDate
     @return java.util.Calendar
     */
    public static synchronized Calendar parseCalendarMilliFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     @param strDate
     @return java.util.Date
     */
    public static synchronized Date parseDateMilliFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
        return parseDateFormat(strDate, pattern);
    }

    /**
     @return String
     */
    public static synchronized String getDateSecondFormat()
    {
        Calendar cal = Calendar.getInstance();
        return getDateSecondFormat(cal);
    }

    /**
     @param cal
     @return String
     */
    public static synchronized String getDateSecondFormat(java.util.Calendar cal)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return getDateFormat(cal, pattern);
    }

    /**
     @param date
     @return String
     */
    public static synchronized String getDateSecondFormat(java.util.Date date)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return getDateFormat(date, pattern);
    }

    public static synchronized String getTimeStamp()
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
 		return format.format(new Date());
    }
    
   
    /**
     @param strDate
     @return java.util.Calendar
     */
    public static synchronized Calendar parseCalendarSecondFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     @param strDate
     @return java.util.Date
     */
    public static synchronized Date parseDateSecondFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return parseDateFormat(strDate, pattern);
    }

    /**
     @return String
     */
    public static synchronized String getDateMinuteFormat()
    {
        Calendar cal = Calendar.getInstance();
        return getDateMinuteFormat(cal);
    }

    /**
     @param cal
     @return String
     */
    public static synchronized String getDateMinuteFormat(java.util.Calendar cal)
    {
        String pattern = "yyyy-MM-dd HH:mm";
        return getDateFormat(cal, pattern);
    }

    /**
     @param date
     @return String
     */
    public static synchronized String getDateMinuteFormat(java.util.Date date)
    {
        String pattern = "yyyy-MM-dd HH:mm";
        return getDateFormat(date, pattern);
    }

    /**
     @param strDate
     @return java.util.Calendar
     */
    public static synchronized Calendar parseCalendarMinuteFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm";
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     @param strDate
     @return java.util.Date
     */
    public static synchronized Date parseDateMinuteFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm";
        return parseDateFormat(strDate, pattern);
    }

    /**
     @return String
     */
    public static synchronized String getDateDayFormat()
    {
        Calendar cal = Calendar.getInstance();
        return getDateDayFormat(cal);
    }

    /**
     @param cal
     @return String
     */
    public static synchronized String getDateDayFormat(java.util.Calendar cal)
    {
        String pattern = "yyyy-MM-dd";
        return getDateFormat(cal, pattern);
    }

    /**
     @param date
     @return String
     */
    public static synchronized String getDateDayFormat(java.util.Date date)
    {
        String pattern = "yyyy-MM-dd";
        return getDateFormat(date, pattern);
    }

    /**
     @param strDate
     @return java.util.Calendar
     */
    public static synchronized Calendar parseCalendarDayFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd";
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     @param strDate
     @return java.util.Date
     */
    public static synchronized Date parseDateDayFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd";
        return parseDateFormat(strDate, pattern);
    }

    /**
     @return String
     */
    public static synchronized String getDateFileFormat()
    {
        Calendar cal = Calendar.getInstance();
        return getDateFileFormat(cal);
    }

    /**
     @param cal
     @return String
     */
    public static synchronized String getDateFileFormat(java.util.Calendar cal)
    {
        String pattern = "yyyy-MM-dd_HH-mm-ss";
        return getDateFormat(cal, pattern);
    }

    /**
     @param date
     @return String
     */
    public static synchronized String getDateFileFormat(java.util.Date date)
    {
        String pattern = "yyyy-MM-dd_HH-mm-ss";
        return getDateFormat(date, pattern);
    }

    /**
     @param strDate
     @return java.util.Calendar
     */
    public static synchronized Calendar parseCalendarFileFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd_HH-mm-ss";
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     @param strDate
     @return java.util.Date
     */
    public static synchronized Date parseDateFileFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd_HH-mm-ss";
        return parseDateFormat(strDate, pattern);
    }

    /**
     @return String
     */
    public static synchronized String getDateW3CFormat()
    {
        Calendar cal = Calendar.getInstance();
        return getDateW3CFormat(cal);
    }

    /**
     @param cal
     @return String
     */
    public static synchronized String getDateW3CFormat(java.util.Calendar cal)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return getDateFormat(cal, pattern);
    }

    /**
     @param date
     @return String
     */
    public static synchronized String getDateW3CFormat(java.util.Date date)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return getDateFormat(date, pattern);
    }

    /**
     @param strDate
     @return java.util.Calendar
     */
    public static synchronized Calendar parseCalendarW3CFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     @param strDate
     @return java.util.Date
     */
    public static synchronized Date parseDateW3CFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return parseDateFormat(strDate, pattern);
    }

    /**
     @param cal
     @return String
     */
    public static synchronized String getDateFormat(java.util.Calendar cal)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return getDateFormat(cal, pattern);
    }

    /**
     @param date
     @return String
     */
    public static synchronized String getDateFormat(java.util.Date date)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return getDateFormat(date, pattern);
    }

    /**
     @param strDate
     @return java.util.Calendar
     */
    public static synchronized Calendar parseCalendarFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return parseCalendarFormat(strDate, pattern);
    }

    /**
     @param strDate
     @return java.util.Date
     */
    public static synchronized Date parseDateFormat(String strDate)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return parseDateFormat(strDate, pattern);
    }
    
    public static synchronized Date getDateFormat(String strDate){
    	String pattern = "yyyy/MM/dd HH:mm:ss";
    	return parseDateFormat(strDate, pattern);
    }

    /**
     @param cal
     @param pattern
     @return String
     */
    public static synchronized String getDateFormat(java.util.Calendar cal, String pattern)
    {
        return getDateFormat(cal.getTime(), pattern);
    }

    /**
     @param date
     @param pattern
     @return String
     */
    public static synchronized String getDateFormat(java.util.Date date, String pattern)
    {
        synchronized (sdf)
        {
            String str = null;
            sdf.applyPattern(pattern);
            str = sdf.format(date);
            return str;
        }
    }

    /**
     @param strDate
     @param pattern
     @return java.util.Calendar
     */
    public static synchronized Calendar parseCalendarFormat(String strDate, String pattern)
    {
        synchronized (sdf)
        {
            Calendar cal = null;
            sdf.applyPattern(pattern);
            try
            {
                sdf.parse(strDate);
                cal = sdf.getCalendar();
            }
            catch (Exception e)
            {
            }
            return cal;
        }
    }

    /**
     @param strDate
     @param pattern
     @return java.util.Date
     */
    public static synchronized Date parseDateFormat(String strDate, String pattern)
    {
        synchronized (sdf)
        {
            Date date = null;
            sdf.applyPattern(pattern);
            try
            {
                date = sdf.parse(strDate);
            }
            catch (Exception e)
            {
            }
            return date;
        }
    }

    public static synchronized int getLastDayOfMonth(int month)
    {
        if (month < 1 || month > 12)
        {
            return -1;
        }
        int retn = 0;
        if (month == 2)
        {
            if (isLeapYear())
            {
                retn = 29;
            }
            else
            {
                retn = dayArray[month - 1];
            }
        }
        else
        {
            retn = dayArray[month - 1];
        }
        return retn;
    }

    public static synchronized int getLastDayOfMonth(int year, int month)
    {
        if (month < 1 || month > 12)
        {
            return -1;
        }
        int retn = 0;
        if (month == 2)
        {
            if (isLeapYear(year))
            {
                retn = 29;
            }
            else
            {
                retn = dayArray[month - 1];
            }
        }
        else
        {
            retn = dayArray[month - 1];
        }
        return retn;
    }

    public static synchronized boolean isLeapYear()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    public static synchronized boolean isLeapYear(int year)
    {
        /**
         * 详细设计�?1.�?00整除是闰年，否则�?2.不能�?整除则不是闰�?3.能被4整除同时不能�?00整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰�?
         */
        if ((year % 400) == 0) return true;
        else if ((year % 4) == 0)
        {
            if ((year % 100) == 0) return false;
            else
                return true;
        }
        else
            return false;
    }

    /**
     * 判断指定日期的年份是否是闰年
     * 
     * @param date
     *            指定日期�?
     * @return 是否闰年
     */
    public static synchronized boolean isLeapYear(java.util.Date date)
    {
        /**
         * 详细设计�?1.�?00整除是闰年，否则�?2.不能�?整除则不是闰�?3.能被4整除同时不能�?00整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰�?
         */
        //      int year = date.getYear();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        int year = gc.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    public static synchronized boolean isLeapYear(java.util.Calendar gc)
    {
        /**
         * 详细设计�?
         * 1.�?00整除是闰年，否则�?
         * 2.不能�?整除则不是闰�?
         * 3.能被4整除同时不能�?00整除则是闰年
         * 4.能被4整除同时能被100整除则不是闰�?
         */
        int year = gc.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * 得到指定日期的前�?��工作�?
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的前�?��工作�?
     */
    public static synchronized java.util.Date getPreviousWeekDay(java.util.Date date)
    {
        {
            /**
             * 详细设计�?
             * 1.如果date是星期日，则�?�?
             * 2.如果date是星期六，则�?�?
             * 3.否则�?�?
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            return getPreviousWeekDay(gc);
            //       switch ( gc.get( Calendar.DAY_OF_WEEK ) )
            //       {
            //        case ( Calendar.MONDAY    ):
            //         gc.add( Calendar.DATE, -3 );
            //         break;
            //        case ( Calendar.SUNDAY    ):
            //         gc.add( Calendar.DATE, -2 );
            //         break;
            //        default:
            //         gc.add( Calendar.DATE, -1 );
            //         break;
            //       }
            //       return gc.getTime();
        }
    }

    public static synchronized java.util.Date getPreviousWeekDay(java.util.Calendar gc)
    {
        {
            /**
             * 详细设计�?
             * 1.如果date是星期日，则�?�?
             * 2.如果date是星期六，则�?�?
             * 3.否则�?�?
             */
            switch (gc.get(Calendar.DAY_OF_WEEK))
            {
                case (Calendar.MONDAY    ) :
                    gc.add(Calendar.DATE, -3);
                    break;
                case (Calendar.SUNDAY    ) :
                    gc.add(Calendar.DATE, -2);
                    break;
                default :
                    gc.add(Calendar.DATE, -1);
                    break;
            }
            return gc.getTime();
        }
    }

    /**
     * 得到指定日期的后�?��工作�?
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的后�?��工作�?
     */
    public static synchronized java.util.Date getNextWeekDay(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.如果date是星期五，则�?�?
         * 2.如果date是星期六，则�?�?
         * 3.否则�?�?
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.DAY_OF_WEEK))
        {
            case (Calendar.FRIDAY    ) :
                gc.add(Calendar.DATE, 3);
                break;
            case (Calendar.SATURDAY    ) :
                gc.add(Calendar.DATE, 2);
                break;
            default :
                gc.add(Calendar.DATE, 1);
                break;
        }
        return gc.getTime();
    }

    public static synchronized java.util.Calendar getNextWeekDay(java.util.Calendar gc)
    {
        /**
         * 详细设计�?
         * 1.如果date是星期五，则�?�?
         * 2.如果date是星期六，则�?�?
         * 3.否则�?�?
         */
        switch (gc.get(Calendar.DAY_OF_WEEK))
        {
            case (Calendar.FRIDAY    ) :
                gc.add(Calendar.DATE, 3);
                break;
            case (Calendar.SATURDAY    ) :
                gc.add(Calendar.DATE, 2);
                break;
            default :
                gc.add(Calendar.DATE, 1);
                break;
        }
        return gc;
    }

    /**
     * 取得指定日期的下�?��月的�?���?��
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的下�?��月的�?���?��
     */
    public static synchronized java.util.Date getLastDayOfNextMonth(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.调用getNextMonth设置当前时间 
         * 2.�?为基�?��调用getLastDayOfMonth
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextMonth(gc.getTime()));
        gc.setTime(DateUtil.getLastDayOfMonth(gc.getTime()));
        return gc.getTime();
    }

    /**
     * 取得指定日期的下�?��星期的最后一�?
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的下�?��星期的最后一�?
     */
    public static synchronized java.util.Date getLastDayOfNextWeek(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.调用getNextWeek设置当前时间 
         * 2.�?为基�?��调用getLastDayOfWeek
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextWeek(gc.getTime()));
        gc.setTime(DateUtil.getLastDayOfWeek(gc.getTime()));
        return gc.getTime();
    }

    /**
     * 取得指定日期的下�?��月的第一�?
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的下�?��月的第一�?
     */
    public static synchronized java.util.Date getFirstDayOfNextMonth(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.调用getNextMonth设置当前时间 
         * 2.�?为基�?��调用getFirstDayOfMonth
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextMonth(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfMonth(gc.getTime()));
        return gc.getTime();
    }

    public static synchronized java.util.Calendar getFirstDayOfNextMonth(java.util.Calendar gc)
    {
        /**
         * 详细设计�?
         * 1.调用getNextMonth设置当前时间 
         * 2.�?为基�?��调用getFirstDayOfMonth
         */
        gc.setTime(DateUtil.getNextMonth(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfMonth(gc.getTime()));
        return gc;
    }

    /**
     * 取得指定日期的下�?��星期的第�?��
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的下�?��星期的第�?��
     */
    public static synchronized java.util.Date getFirstDayOfNextWeek(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.调用getNextWeek设置当前时间 
         * 2.�?为基�?��调用getFirstDayOfWeek
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(DateUtil.getNextWeek(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfWeek(gc.getTime()));
        return gc.getTime();
    }

    public static synchronized java.util.Calendar getFirstDayOfNextWeek(java.util.Calendar gc)
    {
        /**
         * 详细设计�?
         * 1.调用getNextWeek设置当前时间 
         * 2.�?为基�?��调用getFirstDayOfWeek
         */
        gc.setTime(DateUtil.getNextWeek(gc.getTime()));
        gc.setTime(DateUtil.getFirstDayOfWeek(gc.getTime()));
        return gc;
    }

    /**
     * 取得指定日期的下�?���?
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的下�?���?
     */
    public static synchronized java.util.Date getNextMonth(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.指定日期的月份加1
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MONTH, 1);
        return gc.getTime();
    }

    public static synchronized java.util.Calendar getNextMonth(java.util.Calendar gc)
    {
        /**
         * 详细设计�?
         * 1.指定日期的月份加1
         */
        gc.add(Calendar.MONTH, 1);
        return gc;
    }
    
    public static synchronized java.util.Date getPreviousMonth(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.指定日期的月份加1
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MONTH, -1);
        return gc.getTime();
    }
    public static synchronized java.util.Date getPreviousMonth()
    {
        /**
         * 详细设计�?
         * 1.指定日期的月份加1
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.add(Calendar.MONTH, -1);
        return gc.getTime();
    }
    public static synchronized java.util.Calendar getPreviousMonth(java.util.Calendar gc)
    {
        /**
         * 详细设计�?
         * 1.指定日期的月份加1
         */
        gc.add(Calendar.MONTH, -1);
        return gc;
    }

    /**
     * 取得指定日期的下�?��
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的下�?��
     */
    public static synchronized java.util.Date getNextDay(java.util.Date date)
    {
        /**
         * 详细设计�?1.指定日期�?�?
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, 1);
        return gc.getTime();
    }

    public static synchronized java.util.Calendar getNextDay(java.util.Calendar gc)
    {
        /**
         * 详细设计�?1.指定日期�?�?
         */
        gc.add(Calendar.DATE, 1);
        return gc;
    }

    /**
     * 取得指定日期的下�?��星期
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的下�?��星期
     */
    public static synchronized java.util.Date getNextWeek(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.指定日期�?�?
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, 7);
        return gc.getTime();
    }

    public static synchronized java.util.Calendar getNextWeek(java.util.Calendar gc)
    {
        /**
         * 详细设计�?
         * 1.指定日期�?�?
         */
        gc.add(Calendar.DATE, 7);
        return gc;
    }

    /**
     * 取得指定日期的所处星期的�?���?��
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的所处星期的�?���?��
     */
    public static synchronized java.util.Date getLastDayOfWeek(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.如果date是星期日，则�?�?
         * 2.如果date是星期一，则�?�?
         * 3.如果date是星期二，则�?�?
         * 4.如果date是星期三，则�?�?
         * 5.如果date是星期四，则�?�?
         * 6.如果date是星期五，则�?�?
         * 7.如果date是星期六，则�?�?
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.DAY_OF_WEEK))
        {
            case (Calendar.SUNDAY  ) :
                gc.add(Calendar.DATE, 6);
                break;
            case (Calendar.MONDAY  ) :
                gc.add(Calendar.DATE, 5);
                break;
            case (Calendar.TUESDAY  ) :
                gc.add(Calendar.DATE, 4);
                break;
            case (Calendar.WEDNESDAY  ) :
                gc.add(Calendar.DATE, 3);
                break;
            case (Calendar.THURSDAY  ) :
                gc.add(Calendar.DATE, 2);
                break;
            case (Calendar.FRIDAY  ) :
                gc.add(Calendar.DATE, 1);
                break;
            case (Calendar.SATURDAY  ) :
                gc.add(Calendar.DATE, 0);
                break;
        }
        return gc.getTime();
    }

    /**
     * 取得指定日期的所处星期的第一�?
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的所处星期的第一�?
     */
    public static synchronized java.util.Date getFirstDayOfWeek(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.如果date是星期日，则�?�?
         * 2.如果date是星期一，则�?�?
         * 3.如果date是星期二，则�?�?
         * 4.如果date是星期三，则�?�?
         * 5.如果date是星期四，则�?�?
         * 6.如果date是星期五，则�?�?
         * 7.如果date是星期六，则�?�?
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.DAY_OF_WEEK))
        {
            case (Calendar.SUNDAY  ) :
                gc.add(Calendar.DATE, 0);
                break;
            case (Calendar.MONDAY  ) :
                gc.add(Calendar.DATE, -1);
                break;
            case (Calendar.TUESDAY  ) :
                gc.add(Calendar.DATE, -2);
                break;
            case (Calendar.WEDNESDAY  ) :
                gc.add(Calendar.DATE, -3);
                break;
            case (Calendar.THURSDAY  ) :
                gc.add(Calendar.DATE, -4);
                break;
            case (Calendar.FRIDAY  ) :
                gc.add(Calendar.DATE, -5);
                break;
            case (Calendar.SATURDAY  ) :
                gc.add(Calendar.DATE, -6);
                break;
        }
        return gc.getTime();
    }

    public static synchronized java.util.Calendar getFirstDayOfWeek(java.util.Calendar gc)
    {
        /**
         * 详细设计�?
         * 1.如果date是星期日，则�?�?
         * 2.如果date是星期一，则�?�?
         * 3.如果date是星期二，则�?�?
         * 4.如果date是星期三，则�?�?
         * 5.如果date是星期四，则�?�?
         * 6.如果date是星期五，则�?�?
         * 7.如果date是星期六，则�?�?
         */
        switch (gc.get(Calendar.DAY_OF_WEEK))
        {
            case (Calendar.SUNDAY  ) :
                gc.add(Calendar.DATE, 0);
                break;
            case (Calendar.MONDAY  ) :
                gc.add(Calendar.DATE, -1);
                break;
            case (Calendar.TUESDAY  ) :
                gc.add(Calendar.DATE, -2);
                break;
            case (Calendar.WEDNESDAY  ) :
                gc.add(Calendar.DATE, -3);
                break;
            case (Calendar.THURSDAY  ) :
                gc.add(Calendar.DATE, -4);
                break;
            case (Calendar.FRIDAY  ) :
                gc.add(Calendar.DATE, -5);
                break;
            case (Calendar.SATURDAY  ) :
                gc.add(Calendar.DATE, -6);
                break;
        }
        return gc;
    }

    /**
     * 取得指定日期的所处月份的�?���?��
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的所处月份的�?���?��
     */
    public static synchronized java.util.Date getLastDayOfMonth(java.util.Date date)
    {
        /**
         * 详细设计�?
         * 1.如果date�?月，则为31�?
         * 2.如果date�?月，则为28�?
         * 3.如果date�?月，则为31�?
         * 4.如果date�?月，则为30�?
         * 5.如果date�?月，则为31�?
         * 6.如果date�?月，则为30�?
         * 7.如果date�?月，则为31�?
         * 8.如果date�?月，则为31�?
         * 9.如果date�?月，则为30�?
         * 10.如果date�?0月，则为31�?
         * 11.如果date�?1月，则为30�?
         * 12.如果date�?2月，则为31�?
         * 1.如果date在闰年的2月，则为29�?
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.MONTH))
        {
            case 0 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 1 :
                gc.set(Calendar.DAY_OF_MONTH, 28);
                break;
            case 2 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 3 :
                gc.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 4 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 5 :
                gc.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 6 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 7 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 8 :
                gc.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 9 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 10 :
                gc.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 11 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
        }
        //�?��闰年
        if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(gc.get(Calendar.YEAR))))
        {
            gc.set(Calendar.DAY_OF_MONTH, 29);
        }
        return gc.getTime();
    }

    public static synchronized java.util.Calendar getLastDayOfMonth(java.util.Calendar gc)
    {
        /**
         * 详细设计�?
         * 1.如果date�?月，则为31�?
         * 2.如果date�?月，则为28�?
         * 3.如果date�?月，则为31�?
         * 4.如果date�?月，则为30�?
         * 5.如果date�?月，则为31�?
         * 6.如果date�?月，则为30�?
         * 7.如果date�?月，则为31�?
         * 8.如果date�?月，则为31�?
         * 9.如果date�?月，则为30�?
         * 10.如果date�?0月，则为31�?
         * 11.如果date�?1月，则为30�?
         * 12.如果date�?2月，则为31�?
         * 1.如果date在闰年的2月，则为29�?
         */
        switch (gc.get(Calendar.MONTH))
        {
            case 0 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 1 :
                gc.set(Calendar.DAY_OF_MONTH, 28);
                break;
            case 2 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 3 :
                gc.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 4 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 5 :
                gc.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 6 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 7 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 8 :
                gc.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 9 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 10 :
                gc.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 11 :
                gc.set(Calendar.DAY_OF_MONTH, 31);
                break;
        }
        //�?��闰年
        if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(gc.get(Calendar.YEAR))))
        {
            gc.set(Calendar.DAY_OF_MONTH, 29);
        }
        return gc;
    }

    /**
     * 取得指定日期的所处月份的第一�?
     * 
     * @param date
     *            指定日期�?
     * @return 指定日期的所处月份的第一�?
     */
    public static synchronized java.util.Date getFirstDayOfMonth(java.util.Date date)
    {
        /**
         * 详细设计�?1.设置�?�?
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        return gc.getTime();
    }

    public static synchronized java.util.Calendar getFirstDayOfMonth(java.util.Calendar gc)
    {
        /**
         * 详细设计�?1.设置�?�?
         */
        gc.set(Calendar.DAY_OF_MONTH, 1);
        return gc;
    }

    /**
     * 将日期对象转换成为指定ORA日期、时间格式的字符串形式�?如果日期对象为空，返�?�?��空字符串对象，�?不是�?��空对象�?
     * 
     * @param theDate
     *            将要转换为字符串的日期对象�?
     * @param hasTime
     *            如果返回的字符串带时间则为true
     * @return 转换的结�?
     */
    public static synchronized String toOraString(Date theDate, boolean hasTime)
    {
        /**
         * 详细设计�?
         * 1.如果有时间，则设置格式为getOraDateTimeFormat()的返回�?
         * 2.否则设置格式为getOraDateFormat()的返回�? 
         * 3.调用toString(Date theDate, DateFormat
         * theDateFormat)
         */
        DateFormat theFormat;
        if (hasTime)
        {
            theFormat = getOraDateTimeFormat();
        }
        else
        {
            theFormat = getOraDateFormat();
        }
        return toString(theDate, theFormat);
    }

    /**
     * 将日期对象转换成为指定日期�?时间格式的字符串形式。如果日期对象为空，返回 �?��空字符串对象，�?不是�?��空对象�?
     * 
     * @param theDate
     *            将要转换为字符串的日期对象�?
     * @param hasTime
     *            如果返回的字符串带时间则为true
     * @return 转换的结�?
     */
    public static synchronized String toString(Date theDate, boolean hasTime)
    {
        /**
         * 详细设计�?
         * 1.如果有时间，则设置格式为getDateTimeFormat的返回�? 
         * 2.否则设置格式为getDateFormat的返回�?
         * 3.调用toString(Date theDate, DateFormat theDateFormat)
         */
        DateFormat theFormat;
        if (hasTime)
        {
            theFormat = getDateTimeFormat();
        }
        else
        {
            theFormat = getDateFormat();
        }
        return toString(theDate, theFormat);
    }

    /**
     * 标准日期格式
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    /**
     * 标准时间格式
     */
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
            "MM/dd/yyyy HH:mm");
    /**
     * ORA标准日期格式
     */
    private static final SimpleDateFormat ORA_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    /**
     * ORA标准时间格式
     */
    private static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat(
            "yyyyMMddHHmm");
    /**
     * 创建�?��标准日期格式的克�?
     * 
     * @return 标准日期格式的克�?
     */
    public static synchronized DateFormat getDateFormat()
    {
        /**
         * 详细设计�?1.返回DATE_FORMAT
         */
        SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT.clone();
        theDateFormat.setLenient(false);
        return theDateFormat;
    }

    /**
     * 创建�?��标准时间格式的克�?
     * 
     * @return 标准时间格式的克�?
     */
    public static synchronized DateFormat getDateTimeFormat()
    {
        /**
         * 详细设计�?1.返回DATE_TIME_FORMAT
         */
        SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_TIME_FORMAT.clone();
        theDateTimeFormat.setLenient(false);
        return theDateTimeFormat;
    }

    /**
     * 创建�?��标准ORA日期格式的克�?
     * 
     * @return 标准ORA日期格式的克�?
     */
    public static synchronized DateFormat getOraDateFormat()
    {
        /**
         * 详细设计�?1.返回ORA_DATE_FORMAT
         */
        SimpleDateFormat theDateFormat = (SimpleDateFormat) ORA_DATE_FORMAT.clone();
        theDateFormat.setLenient(false);
        return theDateFormat;
    }

    /**
     * 创建�?��标准ORA时间格式的克�?
     * 
     * @return 标准ORA时间格式的克�?
     */
    public static synchronized DateFormat getOraDateTimeFormat()
    {
        /**
         * 详细设计�?1.返回ORA_DATE_TIME_FORMAT
         */
        SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT.clone();
        theDateTimeFormat.setLenient(false);
        return theDateTimeFormat;
    }

    /**
     * 将一个日期对象转换成为指定日期�?时间格式的字符串�?如果日期对象为空，返回一个空字符串，而不是一个空对象�?
     * 
     * @param theDate
     *            要转换的日期对象
     * @param theDateFormat
     *            返回的日期字符串的格�?
     * @return 转换结果
     */
    public static synchronized String toString(Date theDate, DateFormat theDateFormat)
    {
        /**
         * 详细设计�?
         * 1.theDate为空，则返回"" 
         * 2.否则使用theDateFormat格式�?
         */
        if (theDate == null) return "";
        return theDateFormat.format(theDate);
    }
    public static String fotmatDate3(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = formatter.format(myDate);
        return strDate;
      }
    public static String fotmatDate4(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = formatter.format(myDate);
        return strDate;
      }
    public static String fotmatDateYYYYMMDD(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(myDate);
        return strDate;
      }
    public static long getLongTime() {
        Date aDate = new Date();
        long atime = aDate.getTime();
        return atime;
      }
    public static long getDateLongTime(int year, int month, int day) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(year, month - 1, day);
        return myCalendar.getTime().getTime();
      }
    public static void main(String[] args){
    	System.out.println(DateUtil.getDateLongTime(2007,05,02)-DateUtil.getDateLongTime(2007,05,01));
    }
    
    /**
	 * 按照给过来的日期进行格式�?
	 * 
	 * @param myDate
	 * @return
	 */
	public static String fotmatDate14(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	
	/**
	 * 获得当前时间，格式yyyy-MM-dd hh:mm:ss
	 * 
	 * @param format
	 * @return
	 */
	public static Date getCurrentUtilDate() {
		Calendar day = Calendar.getInstance();
		return day.getTime();
	}
	
}
