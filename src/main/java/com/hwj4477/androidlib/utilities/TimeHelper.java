package com.hwj4477.androidlib.utilities;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

public class TimeHelper {
	
	public static final String COMMON_FORMAT = "yyyy.MM.dd HH:mm:ss";
	
	public static final String YEAR_KEY = "year";
	public static final String MONTH_KEY = "month";
	public static final String DAY_KEY = "day";
	public static final String HOUR_KEY = "hour";
	public static final String MINUTE_KEY = "minute";
	public static final String SECOND_KEY = "second";
	
	@SuppressLint("NewApi")
	public static String getTimeString(String strFormat)
	{
		if(strFormat.isEmpty())
		{
			strFormat = COMMON_FORMAT;
		}
		
		SimpleDateFormat sd = new SimpleDateFormat(strFormat);

		String str = sd.format(new Date());
		
		return str;
	}
	
	public static HashMap<String, Object> getNowTime()
	{
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String strDate   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		String strTime   = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
		
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6));
		int day = Integer.parseInt(strDate.substring(6, 8));
		
		int hour = Integer.parseInt(strTime.substring(0, 2));
		int minute = Integer.parseInt(strTime.substring(2, 4));
		int second = Integer.parseInt(strTime.substring(4, 6));
		
		result.put(YEAR_KEY, year);
		result.put(MONTH_KEY, month);
		result.put(DAY_KEY, day);
		result.put(HOUR_KEY, hour);
		result.put(MINUTE_KEY, minute);
		result.put(SECOND_KEY, second);
		
		return result;
	}
	
	public static long getDateBetweenTime(Date fromDate, Date toDate)
	{
		long result = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		
		String from_date = format.format(fromDate);
		String to_date = format.format(toDate);
		
		double fromVal = 0;
		double toVal = 0;
		
		StringTokenizer fromString = new StringTokenizer(from_date, ":" );
		StringTokenizer toString = new StringTokenizer(to_date, ":" );
    	
    	if(fromString.hasMoreTokens())
    	{
    		fromVal += Integer.parseInt(fromString.nextToken()) * 60 * 60;
    		toVal += Integer.parseInt(toString.nextToken()) * 60 * 60;
    	}
    	
    	if(fromString.hasMoreTokens())
    	{
    		fromVal += Integer.parseInt(fromString.nextToken()) * 60;
    		toVal += Integer.parseInt(toString.nextToken()) * 60;
    	}
    	
    	if(fromString.hasMoreTokens())
    	{
    		fromVal += Integer.parseInt(fromString.nextToken());
    		toVal += Integer.parseInt(toString.nextToken());
    	}
    	
    	if(fromVal > toVal)
    	{
    		result = (long) (fromVal - toVal);
    	}
    	else
    	{
    		result = (long) (toVal - fromVal);
    	}
    	
		return result;
	}
	
	public static int getDateBetweenDay(Date fromDate, Date toDate)
	{
		long fromTime = fromDate.getTime();
	    long toTime = toDate.getTime();
	    long oneDay = 1000 * 60 * 60 * 24;
	    long delta = (toTime - fromTime) / oneDay;
	    
	    return (int)delta;
	}
}
