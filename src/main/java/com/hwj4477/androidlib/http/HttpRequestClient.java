package com.hwj4477.androidlib.http;


public class HttpRequestClient {

	/**
	 * 
	 * @author hwj4477@gmail.com
	 * @since 15.2.23
	 *
	 */
	
	public void requestPost(String url, HttpRequestParams params, RequestResultListener listener)
	{
		AsyncHttpPost request = new AsyncHttpPost();
		request.setRequestResultListner(listener);
		request.execute(url, params.getParamValue());
	}
	
	public void requestGet(String url, HttpRequestParams params, RequestResultListener listener)
	{
		AsyncHttpGet request = new AsyncHttpGet();
		request.setRequestResultListner(listener);
		request.execute(url, params.getParamValue());
	}
}
