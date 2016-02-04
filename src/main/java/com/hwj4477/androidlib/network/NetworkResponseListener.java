package com.hwj4477.androidlib.network;

/**
 *
 * @author wjhong
 *
 * @since 15. 4. 30..
 *
 */

public interface NetworkResponseListener {

    public void success(int responseID, String result);
    public void failure(int responseID, String errorString);
}
