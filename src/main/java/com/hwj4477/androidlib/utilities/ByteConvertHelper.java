package com.hwj4477.androidlib.utilities;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * byte[] convert to data helper
 *
 * @author hwj4477@gmail.com
 * @since 13.9.10.
 *
 */

public class ByteConvertHelper {

	
	// convert unsigned data
	public static byte[] uint2bytes(int value)
	{
		byte[] resultByte = {
				(byte) ((value >>> 8) & 0x0000FFFF),
				(byte) (value & 0x000000FF)
		};

		return resultByte;
	}
	
	public static int bytes2uint(byte[] bytes, int from, int to)
	{
		int result = 0;

		int len = to - from;
		
		for(int i=from; i<to; i++)
		{
			int shiftValue = (to - i - 1) * 8;
			
			result += (bytes[i] << shiftValue) & (0x0000FFFF >>> ((len - 1) * 8 - shiftValue));
		}
	
		return result;
	}
	
	public static byte[] ulong2bytes(long value)
	{
		byte[] resultByte = {
				(byte) ((value >>> 24) & 0xFFFFFFFF),
				(byte) ((value >>> 16) & 0x00FFFFFF),
				(byte) ((value >>> 8) & 0x0000FFFF),
				(byte) (value & 0x000000FF)
		};

		return resultByte;
	}
	
	public static long bytes2ulong(byte[] bytes, int from, int to)
	{
		long result = 0;	
		
		int len = to - from;
		
		for(int i=from; i<to; i++)
		{
			int shiftValue = (to - i - 1) * 8;
			
			result += (bytes[i] << shiftValue) & (0xFFFFFFFF >>> ((len - 1) * 8 - shiftValue));
		}
				
		return result;
	}
	
	public static byte[] hash2jsonBytes(Map<String, Object> hash)
	{
		String result = null;
		JSONObject json = new JSONObject(hash);
		try
		{
			result = json.toString(2);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result.getBytes();
	}
	
	public static JSONObject bytes2Json(byte[] bytes, long len, int from, int to)
	{
		JSONObject jsonResult = null;

		byte[] jsonByte = new byte[(int)len];
		for(int i = from, j=0; i<to; i++, j++)
		{
			jsonByte[j] = bytes[i];
		}
		
		String strJson = new String(jsonByte);
		try 
		{
			jsonResult = new 
					JSONObject(strJson);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonResult;
	}
	
	public static byte checkSum(byte[] bytes) 
	{  
		byte sum = 0;  
		for (byte b : bytes) 
		{  
			sum ^= b;  
		}  

		return sum;  
	}  
}
