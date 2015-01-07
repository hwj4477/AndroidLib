package com.hwj4477.androidlib.utilities;

import java.util.Locale;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;

/**
 * 
 * @author hwj4477@gmail.com
 * @since 13.9.10.
 *
 */

public class DeviceUtil {

	public static String getUniqueID(Context context)
	{
		final TelephonyManager tm = (TelephonyManager) ((ContextWrapper) context).getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String deviceId = deviceUuid.toString();
		
		return deviceId;
	}
	
	public static String getAppVersion(Context context)
	{
		String strAppVersion = null;
		
		try 
		{
			strAppVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} 
		catch (NameNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strAppVersion;
	}
	
	public static String getDeviceLocale(Context context)
	{
		Locale locale = context.getResources().getConfiguration().locale;
		
		String lancode = locale.getLanguage();
		
		return lancode;
	}
	
	public static String getPackageName(Context context)
	{
		return context.getPackageName();
	}
	
	public static String getDeviceName()
	{
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		
		if (model.startsWith(manufacturer)) 
		{
			return capitalize(model);
		} 
		else 
		{
			return capitalize(manufacturer) + " " + model;
		}
	}
	
	public static int getDeviceWidth(Context context)
	{
		Display mDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
		
		return mDisplay.getWidth();
	}
	
	public static int getDeviceHeight(Context context)
	{
		Display mDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
		
		return mDisplay.getHeight();
	}
	
	public static int getStatusBarHeight(Context context)
	{
		Rect rectangle= new Rect();
		Window window= ((Activity) context).getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
		int statusBarHeight= rectangle.top;
		
		return statusBarHeight;
	}
	
	@SuppressLint("NewApi")
	public static int getSoftButtonHeight(Context context) 
	{
	    // getRealMetrics is only available with API 17 and +
	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
	        DisplayMetrics metrics = new DisplayMetrics();
	        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
	        int usableHeight = metrics.heightPixels;
	        ((Activity) context).getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
	        int realHeight = metrics.heightPixels;
	        if (realHeight > usableHeight)
	            return realHeight - usableHeight;
	        else
	            return 0;
	    }
	    return 0;
	}
	
	// helper.
	private static String capitalize(String s)
	{
		if (s == null || s.length() == 0)
		{
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) 
		{
			return s;
		} 
		else 
		{
			return Character.toUpperCase(first) + s.substring(1);
		}
	}
}
