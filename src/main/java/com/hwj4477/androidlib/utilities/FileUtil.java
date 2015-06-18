package com.hwj4477.androidlib.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @author hwj4477@gmail.com
 * @since 13.9.10.
 *
 */

public class FileUtil {

	public static String internalStoragePath(Context context)
	{
		return context.getFilesDir().getAbsolutePath();
	}

	public static String externalStoragePath()
	{
		return android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	
	public static void saveBitmapToFile(Bitmap bitmap, String strFilePath)
	{
		File fileCacheItem = new File(strFilePath);
		OutputStream out = null;
		
		try
		{
			fileCacheItem.createNewFile();
			out = new FileOutputStream(fileCacheItem);
			
			bitmap.compress(CompressFormat.JPEG, 100, out);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				out.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static  boolean isExistFile(String strFilePath)
	{
		File file = new File(strFilePath);
		return file.exists();
	}
	
	public static boolean deleteFile(String strFilePath)
	{
		File file = new File(strFilePath);
		if(file.exists())
		{
			return file.delete();
		}
		
		return false;
	}
}
