package com.hwj4477.androidlib.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author hwj4477@gmail.com
 * @since 13.9.12.
 *
 */

public class StringHelper {
	
	public static boolean isNumber(String str)
	{  
        boolean check = true;
        for(int i = 0; i < str.length(); i++)
        {
            if(!Character.isDigit(str.charAt(i)))
            {
                check = false;
                break; 
            }
        }
        return check;  
	}
	
	public static boolean isEmail(String str)
	{
		boolean check = true;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		
		if(!m.matches())
		{
			check = false;
		}
		
		return check;
	}
}

