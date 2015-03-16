
package com.hwj4477.androidlib.network;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;

public class HttpRequest {

	/**
     *
	 * @author hwj4477@gmail.com
	 * @since 15.01.26
     *
     * @used library    : android-async-http.jar
     * @reference       : http://loopj.com/android-async-http/
     *
     * Simple HttpRequest
     *
	 */
	
	private static Context _context = null;
	private static HttpRequest _handler = null;
	private static AsyncHttpClient client = null;

	private HttpRequest()
	{
		client = new AsyncHttpClient();
	}
	
	public synchronized static HttpRequest getInstance(Context context)
	{
		if(_handler == null)
		{
			_context = context;
			_handler = new HttpRequest();
		}
		
		return _handler;
	}

    public void requestPost(String url, RequestParams params, final int responseID, final ResultListener listener)
    {
        client.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response){

                try {
                    listener.getResult(responseID, response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {

                // TODO Auto-generated method stub
                listener.error(responseID);

                super.onFailure(arg0, arg1, arg2, arg3);
            }
        });
    }

    public void requestGet(String url, RequestParams params, final int responseID, final ResultListener listener)
    {
        client.get(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response){

                try {
                    listener.getResult(responseID, response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {

                // TODO Auto-generated method stub
                listener.error(responseID);

                super.onFailure(arg0, arg1, arg2, arg3);
            }
        });
    }

    // Listener
    public interface ResultListener
    {
        public void getResult(int responseID, Object result) throws JSONException;
        public void error(int responseID);
    }
}