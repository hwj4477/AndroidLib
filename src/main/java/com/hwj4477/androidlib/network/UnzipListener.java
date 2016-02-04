package com.hwj4477.androidlib.network;

/**
 *
 * @author wjhong
 *
 * @since 15. 4. 30..
 *
 */

public interface UnzipListener {

    public void success();
    public void failure();
    public void onProgress(int bytesWritten, int totalSize);
}
