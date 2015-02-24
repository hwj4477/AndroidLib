package com.hwj4477.androidlib.http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestParams {
	
	/**
	 * 
	 * @author hwj4477@gmail.com
	 * @since 15.2.23
	 *
	 */
	
	private HashMap<String, String> params;
	
	public HttpRequestParams()
	{
		params = new HashMap<String, String>();
	}
	
	public void put(String key, String value)
	{
		params.put(key, value);
	}
	
	public String getParamValue()
	{
		String strParam = "";
		
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			
			strParam += key + "=" + value;
			
			if(entry != params.entrySet().toArray()[params.entrySet().size()-1])
				strParam += "&";
		}
		
		return strParam;
	}
}
