package com.pony.epidroid.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {
    private final static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private int month;
    private int day;
    private int year;
    private int hours;
    private int minutes;
    private int secondes;

    public DateManager(String fullDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        try {
            Date date = simpleDateFormat.parse(fullDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hours = calendar.get(Calendar.HOUR_OF_DAY);
            minutes = calendar.get(Calendar.MINUTE);
            secondes = calendar.get(Calendar.SECOND);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public final String dateToString() {
        return (getDayString() + " " + day + " " + getMonthString() + " ") + year;
    }

    public final String getMonthString() {
        switch (month) {
            case Calendar.JANUARY:
                return "Janvier";
            case Calendar.FEBRUARY:
                return "Février";
            case Calendar.MARCH:
                return "Mars";
            case Calendar.APRIL:
                return "Avril";
            case Calendar.MAY:
                return "May";
            case Calendar.JUNE:
                return "Juin";
            case Calendar.JULY:
                return "Juillet";
            case Calendar.AUGUST:
                return "Aoput";
            case Calendar.SEPTEMBER:
                return "Septembre";
            case Calendar.OCTOBER:
                return "Octobre";
            case Calendar.NOVEMBER:
                return "Novembre";
            case Calendar.DECEMBER:
                return "Decembre";
        }
        return "";
    }

    public final String getDayString() {
        switch (day) {
            case Calendar.SUNDAY:
                return "Limanche";
            case Calendar.MONDAY:
                return "Lundi";
            case Calendar.TUESDAY:
                return "Mardi";
            case Calendar.WEDNESDAY:
                return "Mercredi";
            case Calendar.THURSDAY:
                return "Jeudi";
            case Calendar.FRIDAY:
                return "Vendredi";
            case Calendar.SATURDAY:
                return "Samedi";
        }
        return "";
    }

    public int getSecondes() {
        return secondes;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public String getHourString() {
        if (hours < 10) {
            return ("0" + hours);
        }
        return "" + hours;
    }

    public String getMinutesString() {
        if (minutes < 10) {
            return ("0" + minutes);
        }
        return "" + minutes;
    }

    public String getSecondesString() {
        if (secondes < 10) {
            return ("0" + secondes);
        }
        return "" + secondes;
    }
}
