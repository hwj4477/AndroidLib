package com.hwj4477.androidlib.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;

public class AsyncHttpGet extends AsyncTask<String, String, String> {
	
	/**
	 * 
	 * @author hwj4477@gmail.com
	 * @since 15.2.23
	 *
	 */
	
	private RequestResultListener listener = null;
	
	public void setRequestResultListner(RequestResultListener listener)
	{
		this.listener = listener;
	}
	
    @Override
    protected String doInBackground(String... params) {
    	String strResult = null;
    	
    	String strUrl = params[0];
    	String strParam = params[1];
        
        try {
            URL url = new URL(strUrl + "?" + strParam);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Common
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            
            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8"); 
            BufferedReader reader = new BufferedReader(tmp); 
            StringBuilder builder = new StringBuilder(); 
            String str; 
            
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            } 
            strResult = builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResult;
    }

    @Override
    protected void onPostExecute(String result) {
    	
    	if(result != null && listener != null) 
    	{
    		listener.onSuccess(result);
    	}
    	else if(listener == null)
    	{
    		listener.onFailed("Litener is Null");
    	}
    	else
    	{
    		listener.onFailed("Failed");
    	}
    }
}
