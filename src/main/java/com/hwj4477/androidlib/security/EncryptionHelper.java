package com.hwj4477.androidlib.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 
 * @author hwj4477@gmail.com
 * @since 13.9.10.
 *
 */

public class EncryptionHelper {

	public static String getMD5(String data)
	{
		try 
		{
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(data.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) 
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} 
		catch (java.security.NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public static String getSha1(String data) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		
		md.update(data.getBytes());
		byte[] digest = md.digest();
		
		StringBuffer sb = new StringBuffer();
		for(byte b : digest)
		{
			String strAppend = Integer.toHexString(b & 0xff);
			
			if(strAppend.length() == 1)
			{
				strAppend = 0 + strAppend;
			}
			
			sb.append(strAppend);
		}
		
		return sb.toString();
	}
}
