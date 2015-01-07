package com.hwj4477.androidlib.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @author hwj4477@gmail.com
 * @since 13.9.12.
 *
 */

public class SharedPreferenceHelper {

	public static boolean saveInt(Context context, String key, int value)
	{
		SharedPreferences preferences =  context.getSharedPreferences(key, Context.MODE_PRIVATE);

		Editor editor = preferences.edit();
		editor.putInt(key, value);
		return editor.commit();
	}

	public static int loadInt(Context context, String key, int defaultValue)
	{
		SharedPreferences preferences =  context.getSharedPreferences(key,Context.MODE_PRIVATE);

		int result = preferences.getInt(key, defaultValue);

		return result;
	}
	
	public static boolean saveFloat(Context context, String key, float value)
	{
		SharedPreferences preferences =  context.getSharedPreferences(key, Context.MODE_PRIVATE);

		Editor editor = preferences.edit();
		editor.putFloat(key, value);
		return editor.commit();
	}

	public static float loadFloat(Context context, String key, float defaultValue)
	{
		SharedPreferences preferences =  context.getSharedPreferences(key,Context.MODE_PRIVATE);

		float result = preferences.getFloat(key, defaultValue);

		return result;
	}
	
	public static boolean saveBoolean(Context context, String key, boolean value)
	{
		SharedPreferences preferences =  context.getSharedPreferences(key, Context.MODE_PRIVATE);

		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}

	public static boolean loadBoolean(Context context, String key, boolean defaultValue)
	{
		SharedPreferences preferences =  context.getSharedPreferences(key,Context.MODE_PRIVATE);

		boolean result = preferences.getBoolean(key, defaultValue);

		return result;
	}
	
	public static boolean saveString(Context context, String key, String value)
	{
		SharedPreferences preferences =  context.getSharedPreferences(key, Context.MODE_PRIVATE);

		Editor editor = preferences.edit();
		editor.putString(key, value);
		return editor.commit();
	}

	public static String loadString(Context context, String key, String defaultValue)
	{
		SharedPreferences preferences =  context.getSharedPreferences(key,Context.MODE_PRIVATE);

		String result = preferences.getString(key, defaultValue);

		return result;
	}
}
