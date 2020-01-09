package com.tz.clockontools.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private final static String YYYY_MM_DD = "yyyy-MM-dd";
    private final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    private final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    private final static String HH_mm = "HH:mm";
    private final static String HH_mm_ss = "HH:mm:ss";

    /**
     * 验证字符串是否为时间格式
     *
     * @param str    字符串
     * @param format 时间格式  yyyy/MM/dd  yyyy-HH-mm HH:mm:ss
     * @return
     */
    public static boolean isValidDate(String str, String format) {
        boolean convertSuccess = true;
        try {
            // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
            SimpleDateFormat _format = new SimpleDateFormat(format);
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2017/02/29会被接受，并转换成2017/03/01
            _format.setLenient(false);
            _format.parse(str);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 判断是否包含时分秒
     *
     * @param time
     * @return
     */
    public static boolean isContainTime(long time) {
        //yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH");//时
        SimpleDateFormat sdf2 = new SimpleDateFormat("mm");//分
        SimpleDateFormat sdf3 = new SimpleDateFormat("ss");//秒
        boolean b = false;
        String str1 = sdf1.format(new Date(time));
        String str2 = sdf2.format(new Date(time));
        String str3 = sdf3.format(new Date(time));
        int strInt1 = Integer.valueOf(str1);
        int strInt2 = Integer.valueOf(str2);
        int strInt3 = Integer.valueOf(str3);
        int strInt = strInt1 + strInt2 + strInt3;
        if (strInt > 0) {
            b = true;
        }
        return b;
    }

    /**
     * 根据日期字符串获取Timestamp
     *
     * @param strDate 日期字符串（format：yyyy-MM-dd）
     * @return
     */
    public static Timestamp getTimestampOfDate(String strDate) {
        Date date = getYYYYMMDD(strDate);
        return new Timestamp(date.getTime());
    }

    /**
     * 根据日期时间字符串获取Timestamp
     *
     * @param yyyyMMddHHmmSS 日期字符串（format：yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static Timestamp getTimestampOfDateTime(String yyyyMMddHHmmSS) {
        if (yyyyMMddHHmmSS == null || yyyyMMddHHmmSS.equals("")) {
            return null;
        } else {
            Date date = getYyyyMMddHHmmss(yyyyMMddHHmmSS);
            return new Timestamp(date.getTime());
        }
    }

    /**
     * 根据日期字符串获取获取Date
     *
     * @param yyyyMMdd 日期字符串（format：yyyy-MM-dd）
     * @return
     */
    public static Date getYYYYMMDD(String yyyyMMdd) {
        Date date;
        try {
            date = new SimpleDateFormat(YYYY_MM_DD).parse(yyyyMMdd);
        } catch (ParseException e) {
            date = new Date(946656000000L);//2000 01 01
        }
        return date;
    }

    /**
     * 根据日期字符串获取获取Date
     *
     * @param yyyyMMddHHmm 日期字符串（format：yyyy-MM-dd HH:mm）
     * @return
     */
    public static Date getYyyyMMddHHmm(String yyyyMMddHHmm) {
        Date date;
        try {
            date = new SimpleDateFormat(yyyy_MM_dd_HH_mm).parse(yyyyMMddHHmm);
        } catch (ParseException e) {
            date = new Date(946656000000L);//2000 01 01
        }
        return date;
    }

    /**
     * 根据日期字符串获取获取Date
     *
     * @param yyyyMMddHHmmSS 日期字符串（format：yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static Date getYyyyMMddHHmmss(String yyyyMMddHHmmSS) {
        Date date;
        try {
            date = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss).parse(yyyyMMddHHmmSS);
        } catch (ParseException e) {
            date = new Date(946656000000L);//2000 01 01
        }
        return date;
    }

    /**
     * 获取当前日期字符串
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDateString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取当前日期时间字符串
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * @param timestamp
     * @return HH:mm
     */
    public static String getHHMMString(Timestamp timestamp) {
        try {
            return new SimpleDateFormat(HH_mm).format(new Date(timestamp.getTime()));
        } catch (Exception e) {
            return new SimpleDateFormat(HH_mm).format(new Date(946656000000L));//2000 01 01
        }
    }

    /**
     * @param yyyyMMddHHmmSS 日期字符串（format：yyyy-MM-dd HH:mm:ss）
     * @return HH:mm:ss
     */
    public static String getHHMMssString(String yyyyMMddHHmmSS) {
        Timestamp timestamp = getTimestampOfDateTime(yyyyMMddHHmmSS);
        return timestamp.getHours() + ":" + timestamp.getMinutes()
                + ":" + timestamp.getSeconds();
    }

    /**
     * @param yyyyMMddHHmmSS 日期字符串（format：yyyy-MM-dd HH:mm:ss）
     * @return HH:mm
     */
    public static String getHHMMString(String yyyyMMddHHmmSS) {
        Timestamp timestamp = getTimestampOfDateTime(yyyyMMddHHmmSS);
        return timestamp.getHours() + ":" + timestamp.getMinutes();
    }

    /**
     * @param timestamp
     * @return HH:mm:ss
     */
    public static String getHHMMssString(Timestamp timestamp) {
        try {
            return new SimpleDateFormat(HH_mm_ss).format(new Date(timestamp.getTime()));
        } catch (Exception e) {
            return new SimpleDateFormat(HH_mm_ss).format(new Date(946656000000L));//2000 01 01
        }
    }

    /**
     * @param time 毫秒
     * @return yyyy-MM-dd
     */
    public static String getYYYYMMDDString(Long time) {
        try {
            return new SimpleDateFormat(YYYY_MM_DD).format(new Date(time));
        } catch (Exception e) {
            return new SimpleDateFormat(YYYY_MM_DD).format(new Date(946656000000L));//2000 01 01
        }
    }

    /**
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getYYYYMMDDString(int year, int month, int day) {
        return year + (month < 10 ? "-0" + month : "-" + month) + (day < 10 ? "-0" + day : "-" + day);
    }

    /**
     * @param timestamp
     * @return yyyy-MM-dd
     */
    public static String getYYYYMMDDString(Timestamp timestamp) {
        try {
            return new SimpleDateFormat(YYYY_MM_DD).format(new Date(timestamp.getTime()));
        } catch (Exception e) {
            return new SimpleDateFormat(YYYY_MM_DD).format(new Date(946656000000L));//2000 01 01
        }
    }

    /**
     * @param date
     * @return yyyy-MM-dd
     */
    public static String getYYYYMMDDString(Date date) {
        try {
            return new SimpleDateFormat(YYYY_MM_DD).format(date);
        } catch (Exception e) {
            return new SimpleDateFormat(YYYY_MM_DD).format(new Date(946656000000L));//2000 01 01
        }
    }

    /**
     * @param timestamp
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getYyyyMMddHHmmssString(Timestamp timestamp) {
        try {
            return new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss).format(new Date(timestamp.getTime()));
        } catch (Exception e) {
            return "";//2000 01 01
        }
    }

    /**
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getYyyyMMddHHmmssString(Date date) {
        try {
            return new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss).format(date.getTime());
        } catch (Exception e) {
            return "";//2000 01 01
        }
    }

    /**
     * @param date
     * @return yyyy-MM-dd HH:mm
     */
    public static String getYyyyMMddHHmmString(Date date) {
        try {
            return new SimpleDateFormat(yyyy_MM_dd_HH_mm).format(date);
        } catch (Exception e) {
            return "";//2000 01 01
        }
    }

    /**
     * @param time     毫秒
     * @param complete 完整格式（即：包含时间）
     * @return yyyy-MM-dd or yyyy-MM-dd HH:mm:ss
     */
    public static String getYyyyMMddOrYyyyMMddHHmmssString(Long time, boolean complete) {
        try {
            return complete ? new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss).format(new Date(time)) : new SimpleDateFormat(YYYY_MM_DD).format(new Date(time));
        } catch (Exception e) {
            return new SimpleDateFormat(YYYY_MM_DD).format(new Date(946656000000L));//2000 01 01
        }
    }

    /**
     * 获取与当前时间相比已经过去多少时间
     *
     * @param time 毫秒
     * @return 1分钟前|1天前|1年前
     */
    public static String getTimeAgo(Long time) {
        String result = "";
        if (time != null) {
            long now = Calendar.getInstance().getTimeInMillis();
            long dateTime = (now - time) / 1000;
            if (dateTime > 365 * 24 * 60 * 60) {
                result = (int) (dateTime / (365 * 24 * 60 * 60)) + "年前";
            } else if (dateTime > 24 * 60 * 60) {
                result = (int) (dateTime / (24 * 60 * 60)) + "天前";
            } else if (dateTime > 60 * 60) {
                result = (int) (dateTime / (60 * 60)) + "小时前";
            } else if (dateTime > 60) {
                result = (int) (dateTime / (60)) + "分钟前";
            } else if (dateTime > 1) {
                result = dateTime + "秒前";
            } else {
                result = "1秒前";
            }
        }
        return result;
    }

    /**
     * 评论时间显示规则
     * 当天评论时间显示举例 ：  1分钟前、1小时前、23小时前
     * 当年第二天时间显示为 月日+时间
     * 前一年时间显示为 年月日+时间
     *
     * @param time
     * @return
     */
    public static String getDateTimeAgo(Long time) {
        String result = "";
        if (time != null) {
            long now = Calendar.getInstance().getTimeInMillis();
            long dateTime = (now - time) / 1000;
            if (dateTime > 365 * 24 * 60 * 60) {
                //年月日+时间
                result = getYyyyMMddOrYyyyMMddHHmmssString(time, true);
            } else if (dateTime > 24 * 60 * 60) {
                //月日+时间
                String strDateTime = getYyyyMMddOrYyyyMMddHHmmssString(time, true);
                result = strDateTime.substring(5, strDateTime.length());
            } else if (dateTime > 60 * 60) {
                result = (int) (dateTime / (60 * 60)) + "小时前";
            } else if (dateTime > 60) {
                result = (int) (dateTime / (60)) + "分钟前";
            } else if (dateTime > 1) {
                result = dateTime + "秒前";
            } else {
                result = "1秒前";
            }
        }
        return result;
    }

    /**
     * 获取最小Date
     *
     * @return
     */
    public static Date getMinDate() {
        return new Date(946656000000L);//2000 01 01
    }

    /**
     * 获取当前Timestamp
     *
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前毫秒数
     *
     * @return
     */
    public static long getCurrentMilliseconds() {
        return getCurrentTimestamp().getTime();
    }

    /**
     * 获取指定小时前的毫秒数据
     *
     * @param hours
     * @return
     */
    public static long getMillisecondsBeforeNHours(int hours) {
        return getCurrentMilliseconds() - (hours * 60 * 60 * 1000);
    }


    /**
     * 获取年龄
     *
     * @param birthDay 生日
     * @return
     */
    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 时间/日期/周/月/年 加减
     *
     * @param date  开始加减的时间
     * @param type  日期类型，如：Calendar.DATE、Calendar.FIELD_NAME_MONTH
     * @param value 加减幅度，如：1、32
     * @return
     */
    public static Date dateAdd(Date date, int type, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, value);
        return calendar.getTime();
    }


    /**
     * 根据自定义的周期单位返回java.util定义的单位
     *
     * @param type 干预周期单位：1-天，2-周，3-月，4-年
     * @return
     */
    public static int dateType(int type) {
        int defaultValue = Calendar.YEAR;
        if (type == 1) {
            defaultValue = Calendar.DATE;
        } else if (type == 2) {
            defaultValue = Calendar.WEEK_OF_YEAR;
        } else if (type == 3) {
            defaultValue = Calendar.MONTH;
        } else if (type == 4) {
            defaultValue = Calendar.YEAR;
        }

        return defaultValue;
    }

    /**
     * 周期计算  根据给定的时间段计算周期（不足一个周期的，按照一个周期算）
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @param type      周期单位，对应java.util 里面的时间周期类型  Calendar.FIELD_NAME_YEAR/Calendar.FIELD_NAME_MONTH
     * @param unit      周期数
     * @return
     */
    public static int countPeriod(Date beginDate, Date endDate, int type, int unit) {
        int c = 0;
        int u = unit;
        if (beginDate.getTime() >= endDate.getTime()) {
            return c;
        }
        while (true) {
            Date date1 = dateAdd(beginDate, type, u);
            u += unit;
            c++;
            if (date1.getTime() >= endDate.getTime()) {
                break;
            }
        }
        return c;
    }


    /**
     * 获取月份的两位数字符串，即，MM
     *
     * @param month
     * @return
     */
    private static String getMonthString(int month) {
        if (month < 10) {
            return "0" + month;
        } else {
            return month + "";
        }
    }

    /**
     * 判断是否过期
     *
     * @param timestamp milliseconds 时间戳
     * @param timeout   milliseconds 超时时间
     * @return
     */
    public static boolean isExpired(long timestamp, long timeout) {
        //当前时间(milliseconds)
        long current = new Date().getTime();
        //当前时间和时间戳的差值
        long difference = current - timestamp;
        //如果超过了15分钟，则过期了
        return difference > timeout;
    }
}
