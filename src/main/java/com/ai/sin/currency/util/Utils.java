package com.ai.sin.currency.util;

import com.ai.sin.currency.exception.InvalidDateFormatException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilities class
 */
public final class Utils {

    public static final String RATE_FILE_SUFFIX = ".TXT";

    public static String getDateFromFileName(String fileName) {
        fileName = fileName.toUpperCase();
        return fileName.substring(0, fileName.lastIndexOf(RATE_FILE_SUFFIX));
    }

    /**
     * Is adate between start and end
     * @param aDate given date
     * @param start start date
     * @param end end date
     * @return  start <= aDate <= end
     */
    public static boolean isDateBetween(Date aDate, Date start, Date end) {
        return (aDate.after(start) || aDate.equals(start)) && (aDate.before(end) || aDate.equals(end));
    }

    /**
     * Parse date
     * @param date - expected date format
     * @return
     * @throws InvalidDateFormatException if date format is not as expected
     */
    public static Date parseDate(String date, String dateFormat) throws InvalidDateFormatException {
        try {
            return new SimpleDateFormat(dateFormat).parse(date);
        }catch(Exception e) {
            throw new InvalidDateFormatException("The date " + date + " is not expected date format as " + dateFormat, e);
        }
    }
}
