package com.pony.epidroid.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hervie_g on 2/3/15.
 */
public class CalendarUtils {
    public final static String IN_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String OUT_DATE_FORMAT = "EEEE dd MMMM yyyy";
    public final static String OUT_HOUR_FORMAT = "HH:mm";

    public static Date parse(String fullDate, Locale locale) throws ParseException {
        DateFormat format = new SimpleDateFormat(IN_FORMAT, locale);

        return format.parse(fullDate);
    }

    public static Date parse(String fullDate) throws ParseException {
        return parse(fullDate, Locale.getDefault());
    }

    public static String formatDate(Date date, String fmt, Locale locale) {
        DateFormat format = new SimpleDateFormat(fmt, locale);

        return format.format(date);
    }

    public static String formatDate(Date date, String fmt) {
        return formatDate(date, fmt, Locale.getDefault());
    }
}
