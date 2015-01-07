package com.hwj4477.androidlib.utilities;

import java.util.HashMap;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmHelper {

	public static void registAlarm(Context context, Class<?> target, int code, long time, HashMap<String, Object> data)
	{
		Intent intent = new Intent(context, target);
		
		for (String key : data.keySet())
		{
			intent.removeExtra(key);
			intent.putExtra(key, String.valueOf(data.get(key)));
		}
		
	    PendingIntent sender = PendingIntent.getBroadcast(context, code, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	         
	    AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	         
	    manager.set(AlarmManager.RTC_WAKEUP, time, sender);
		
	}
	
	public static void unRegistAlarm(Context context, int code)
	{
		Intent intent = new Intent();
		PendingIntent sender = PendingIntent.getBroadcast(context, code, intent, 0);

		AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		manager.cancel(sender);
	}
}
