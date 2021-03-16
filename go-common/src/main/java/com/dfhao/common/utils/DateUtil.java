package com.dfhao.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author :  dfhao
 * @date :  2021/3/16 22:46
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 日期
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT2 = "yyyyMMdd";
    /**
     * 日期时间
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT2 = "yyyyMMddHHmmss";
    /**
     * 日期时间戳
     */
    public static final String DATETIME_STAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATETIME_STAMP_FORMAT2 = "yyyyMMddHHmmssSSS";
    /**
     * 时间格式器
     */
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String TIME_FORMAT2 = "HHmmss";
    public static final String TIME_STAMP = "HH:mm:ss.SSS";
    public static final String TIME_STAMP2 = "HHmmssSSS";
    /**
     * UTC时间格式化器
     */
    public static final String UTC_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    /**
     * UTC时间戳
     */
    public static final String UTC_DATETIME_STAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    /**
     * GMT时间格式化器
     */
    public static final String GMT_DATETIME_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";

    /**
     * 将字符串解析成自定义格式的日期时间
     *
     * @param value  字符串
     * @param format 格式
     * @return 日期
     */
    public static Date parse(String value, String format) {
        if (!StringUtils.hasText(value) || !StringUtils.hasText(format)) {
            return null;
        }
        try {
            return new SimpleDateFormat(format, Locale.CHINA).parse(value);
        } catch (ParseException e) {
            logger.error("DateUtil-系统异常", e);
            return null;
        }
    }

    /**
     * 将字符串解析成 yyyy-MM-dd 的日期
     *
     * @param value 日期字符串
     * @return 日期
     */
    public static Date parseDate(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return new SimpleDateFormat(DATE_FORMAT, Locale.CHINA).parse(value);
        } catch (ParseException e) {
            logger.error("DateUtil-系统异常", e);
            return null;
        }
    }

    /**
     * 将字符串解析成 yyyy-MM-dd HH:mm:ss 的日期时间
     *
     * @param value 时间字符串
     * @return 日期
     */
    public static Date parseDateTime(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return new SimpleDateFormat(DATETIME_FORMAT, Locale.CHINA).parse(value);
        } catch (ParseException e) {
            logger.error("DateUtil-系统异常", e);
            return null;
        }
    }

    /**
     * 自定义日期格式化
     *
     * @param date   日期
     * @param format 格式
     * @return 字符串
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(format, Locale.CHINA).format(date);
    }

    /**
     * 将日期格式化成 yyyy-MM-dd 的字符串
     *
     * @param date 日期
     * @return 字符串
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT, Locale.CHINA).format(date);
    }

    /**
     * 将日期时间格式化成 yyyy-MM-dd HH:mm:ss 的字符串
     *
     * @param date 日期
     * @return 字符串
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATETIME_FORMAT, Locale.CHINA).format(date);
    }

    /**
     * utc时间解析为date
     *
     * @param value "2004-08-04T19:09:02.768Z"
     * @return 日期
     */
    public static Date parseUTCDateTimeStamp(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(UTC_DATETIME_STAMP_FORMAT);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            return format.parse(value);
        } catch (ParseException e) {
            logger.error("DateUtil-系统异常", e);
            return null;
        }
    }

    /**
     * utc时间格式化为字符串
     *
     * @param date utc时间
     * @return 字符串
     */
    public static String formatUTCDateTimeStamp(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(UTC_DATETIME_STAMP_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(date);
    }

    /**
     * 把日期字符串转为UTC日期字符串
     *
     * @param dateStr 日期字符串
     * @return UTC日期字符串
     */
    public static String formatUTCDateTimeStamp(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat(UTC_DATETIME_STAMP_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(parseDateTime(dateStr));
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间字符串
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_FORMAT);
        return formatter.format(date);
    }

    /**
     * 获取当年天数
     * 根据闰年进行判断
     *
     * @return 当年天数
     */
    public static int getDaysInYear() {
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.isLeapYear(calendar.get(Calendar.YEAR)) ? 366 : 365;
    }

    /**
     * @param date         指定日期
     * @param calendarType Calendar.DATE、Calendar.MONTH、Calendar.YEAR...
     * @param num          对应日期类型对应的增量（负数表示减量）
     * @return 比如：30天后的日期等
     */
    public static Date getAfterDate(Date date, int calendarType, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarType, num);
        return cal.getTime();
    }
}
