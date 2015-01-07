package com.hwj4477.androidlib.helper;

import java.text.DecimalFormat;

public class StringFormatter {

	/**
	 * 
	 * @author hwj4477@gmail.com
	 * @since 14.6.10
	 *
	 */
	
	public static String getMoneyFormat(int value)
	{
		DecimalFormat df = new DecimalFormat("#,##0");
		
		return df.format(value);
	}
}
