package com.hwj4477.androidlib.http;

public interface RequestResultListener {

	/**
	 * 
	 * @author hwj4477@gmail.com
	 * @since 15.2.23
	 *
	 */
	
	void onSuccess(String result);
	void onFailed(String reasonMassage);
}
