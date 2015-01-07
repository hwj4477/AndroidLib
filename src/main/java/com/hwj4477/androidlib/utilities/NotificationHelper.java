package com.hwj4477.androidlib.utilities;

import java.util.HashMap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationHelper {

	public static void setNotification(Context context, Class<?> target, int notiId, int icon, String ticker, String title, String message, long time, HashMap<String, Object> data)
	{
		NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Intent notifIntent = new Intent(context, target);
		
		if(data != null)
		{
			for (String key : data.keySet())
			{
				notifIntent.putExtra(key, (String) data.get(key));
			}
		}
		
		PendingIntent intent = PendingIntent.getActivity(context, 0, notifIntent, 0);
		
		// Create Notification Object
		Notification notification = new Notification(android.R.drawable.ic_input_add, ticker, time);
		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_VIBRATE;
	    notification.defaults |= Notification.DEFAULT_SOUND;
		
		nm.notify(notiId, notification);
	}
	
	public static void cancelNotification(Context context, int notifId)
	{
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		// Cancel Notification
		nm.cancel(notifId);
	}
}
