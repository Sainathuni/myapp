package org.saibaba.fw.utils;

// Java classes
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.Validate;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    // Various date formatting constants
    public final static String MM = "MM";
    public final static String MMDD = "MM/dd";
    public final static String YYYY = "yyyy";
    public final static String YY = "yy";
    public final static String MMYYYY = "MM/yyyy";
    public final static String MMDDYYYY = "MM/dd/yyyy";
    public final static String MMDDYYYYHHMMSS = "MM/dd/yyyy h:mm:ssa";
    public final static String MMDDYYYYHHMM_EDIT = "MM/dd/yyyy HH:mm";
    public static final String YYMMDDHHMM = "yyMMddHHmm";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String DDMMMHHMMYYYY = "dd MMM HH:mm yyyy";
    public static final String DDMMMYYYYHHMM = "dd MMM yyyy HH:mm";
    public static final String DAY_DD_MONTH_YEAR =  "EEE, d MMM yyyy HH:mm:ss z";
    public static final String HHMM="HHmm";
    public static final String DATEWITHMILLIES = "yyyyMMdd HH:mm:ss SSS";
    public static final String DDMMMYYYY = "dd MMM yyyy";
    

    
	/**
	 * A default constructor
	 * 
	 */
	private DateUtils() {
		super();
	}
	

	 public static String format(Date date, String format)
	 {
		 return format(date, format, false);
	 }

   
    public static String format(Date date, String format, boolean toUpper)
    {
    	String formattedString = null;
        if (date == null)
        {
            return null;
        }
        if (StringUtils.isBlank(format))
        {
            format = MMDDYYYY;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        formattedString = sdf.format(date);
        if(toUpper)
        {
        	formattedString = formattedString.toUpperCase();
        }
        return formattedString;
    }
    
	
	
    public static boolean isDateInYear(Integer year, Date date) {
        Validate.notNull( year, "A year code must not be null" );
        Validate.notNull( date, "A date must not be null" );

        Calendar start = Calendar.getInstance();
        start.set(year.intValue(), Calendar.JANUARY, 1, 0, 0, 0);
        
        Calendar end = Calendar.getInstance();
        end.set(year.intValue(), Calendar.DECEMBER, 31, 23, 59, 59);
       
        return isDateBetween(date, start.getTime(), end.getTime());
       
    }  
  

    public static boolean isDateBetween(Date date, Date start, Date end) {
        boolean inBetween = false;
        if( (start.equals( date ) || start.before( date )) &&
             (end.equals( date ) || end.after( date )) ) {
            inBetween = true;
        }
        return inBetween;
    }
	
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.clear();
        calendar.set(year,month,day);
        return calendar.getTime();
    }

    public static Date getCurrentDateTime()
    {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
    
    public static Timestamp getCurrentTimestamp()
    {
        Calendar calendar = Calendar.getInstance();
        return new Timestamp(calendar.getTime().getTime());
    }
    

    public static Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.clear();
        calendar.set(year,month,day);
        return calendar.getTime();
    }

    public static Date getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.clear();
        calendar.set(year,month,day);
        return calendar.getTime();
    }
    
  
}