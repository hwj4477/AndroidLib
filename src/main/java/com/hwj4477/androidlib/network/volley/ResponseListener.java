package com.hwj4477.androidlib.network.volley;

import org.json.JSONException;

/**
 * @author wjhong
 * @since 2016. 2. 2..
 */
public interface ResponseListener {

    void success(int resultCode, Object result) throws JSONException, ClassCastException;
    void failed(String errorMessage);
    void failed(int resultCode, String extraMessage);
}
