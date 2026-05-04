package com.homefun.common.utils;

import java.util.Date;

public class DateUtils {

    public static final String YYYY = "yyyy";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String formatDate(Date date) {
        return format(date, YYYY_MM_DD);
    }

    public static String formatDateTime(Date date) {
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }

    public static Date parse(String dateStr, String pattern) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDate(String dateStr) {
        return parse(dateStr, YYYY_MM_DD);
    }

    public static Date parseDateTime(String dateStr) {
        return parse(dateStr, YYYY_MM_DD_HH_MM_SS);
    }

    public static Date now() {
        return new Date();
    }

    public static String getNowStr() {
        return formatDateTime(now());
    }
}
