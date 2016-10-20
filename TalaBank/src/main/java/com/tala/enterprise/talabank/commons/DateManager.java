/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tala.enterprise.talabank.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author kimando
 */
public class DateManager {
    
    public static Date getDate(String format) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat(format);
	Date date = new Date();
        //return dateFormat.parse(date.toString());
        return DateUtils.parseDate(dateFormat.format(date), format);
    }
    
    public static boolean hasExpired(Date startDate, int days) {
        return new Date().after(DateUtils.addDays(startDate, days));
    }
    
    public static Date addDays(Date startDate, int days){
        return DateUtils.addDays(startDate, days);
    }
    
    public static String parseDateFromDateToString(Date date, String format) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
    
    public static String parseDateFromStringToString(String date, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date(date));
    }
        
    public static Date parseDateFromStringToDate(String date, String format) throws ParseException{
        return DateUtils.parseDate(date, format);
    }
}
