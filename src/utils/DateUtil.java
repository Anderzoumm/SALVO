package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat formatter;

    public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";
    public static final String TIME_PATTERN = "HH:mm";

    public static String dateToString(Date date, String pattern){
        formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String dateToString(Date date){
        formatter = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return formatter.format(date);
    }

    public static Date stringToDate(String dateString){
        return new Date();
    }
}
