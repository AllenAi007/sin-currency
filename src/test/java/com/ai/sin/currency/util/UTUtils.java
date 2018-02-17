package com.ai.sin.currency.util;

import com.ai.sin.currency.exception.InvalidDateFormatException;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Test Utilities class
 */
public class UTUtils {

    @Test
    public void testGetDateFromFileName(){
        String fileName = "2012-12-01.txt";
        String expect = "2012-12-01";
        assertEquals(Utils.getDateFromFileName(fileName), expect);
    }

    @Test
    public void testParseDate() throws Exception {
        String date = "2012-10-11";
        String format = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        assertEquals(simpleDateFormat.format(Utils.parseDate(date, format)), date);
    }

    /**
     * Negative test, expect a InvalidDateFormatException exception
     * @throws Exception InvalidDateFormatException
     */
    @Test(expected = InvalidDateFormatException.class)
    public void testParseDateNeg() throws Exception {
        String date = "2012-Dec-11";
        String format = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        assertEquals(simpleDateFormat.format(Utils.parseDate(date, format)), date);
    }

    @Test
    public void testIsDateBetween() throws Exception {
        String format = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        Date aDate = simpleDateFormat.parse("2018-01-10");
        Date start = simpleDateFormat.parse("2018-01-01");
        Date end = simpleDateFormat.parse("2018-01-20");
        assertTrue(Utils.isDateBetween(aDate, start, end));

        aDate = simpleDateFormat.parse("2018-01-01");
        assertTrue(Utils.isDateBetween(aDate, start, end));

        aDate = simpleDateFormat.parse("2018-01-20");
        assertTrue(Utils.isDateBetween(aDate, start, end));

        aDate = simpleDateFormat.parse("2018-01-25");
        assertFalse(Utils.isDateBetween(aDate, start, end));


    }
}
