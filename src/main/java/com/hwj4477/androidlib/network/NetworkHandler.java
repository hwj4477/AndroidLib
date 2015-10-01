package com.stn.common.network;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author wjhong
 *
 * referenced : https://github.com/loopj/android-async-http
 *
 * @since 15. 4. 30..
 *
 */

public class NetworkHandler {

    private static final int LOADING_TIMEOUT = 10000;

    private static AsyncHttpClient client = null;

    private static ProgressDialog loadingDialog = null;

    private static boolean isLoading = false;

    public NetworkHandler()
    {
        client = new AsyncHttpClient(true, 80, 443);
        client.addHeader(AsyncHttpClient.HEADER_CONTENT_TYPE, "application/json");
    }

    public void requestGet(final int requestID, String strUrl, RequestParams params, final NetworkResponseListener listener) {
        client.get(strUrl, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

                listener.success(requestID, new String(bytes));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                listener.failure(requestID, new String(bytes));
            }
        });
    }

    // Request.
    public void requestPost(final int requestID, String strUrl, RequestParams params, final NetworkResponseListener listener) {
        client.post(strUrl, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

                listener.success(requestID, new String(bytes));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                listener.failure(requestID, new String(bytes));
            }
        });
    }

    public void requestPostRaw(Context context, final int requestID, String strUrl, JSONObject param, final NetworkResponseListener listener) {

        try {

            StringEntity entity = new StringEntity(param.toString());

            client.post(context, strUrl, entity, "application/json", new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {

                    listener.success(requestID, new String(bytes));
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    listener.failure(requestID, new String(bytes));
                }
            });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    // Download.
    public void downloadFile(Context context, String url, final String toPath, final String name, final FileDownloadListener listener)
    {
        client.get(url, new FileAsyncHttpResponseHandler(context) {

            @Override
            public void onSuccess(int i, Header[] headers, File file) {

                int size = (int) file.length();
                byte[] bytes = new byte[size];

                BufferedInputStream buf = null;

                try {
                    buf = new BufferedInputStream(new FileInputStream(file));
                    buf.read(bytes, 0, bytes.length);
                    buf.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                File dir = new File(toPath);
                dir.mkdirs();
                File saveFile = new File(toPath, name);

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(saveFile);
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listener.success();
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, File file) {

                listener.failure();
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                int progressPercentage = (int)100*bytesWritten / totalSize;

                listener.onProgress(bytesWritten, totalSize);
            }
        });
    }

    // Unzip.
    public void unZipFile(String filePath, String toPath, final UnzipListener listener)
    {
        UnzipAsyncTask unzip = new UnzipAsyncTask(filePath, toPath, listener);

        unzip.execute();
    }

    // Loading Helper.
    public void showLoading(Context context, String message)
    {
        if(!isLoading)
        {
            loadingDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
            loadingDialog.setMessage(message);
            loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);

            isLoading = true;

            loadingDialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    hideLoading();
                }
            }, LOADING_TIMEOUT);
        }
    }

    public void hideLoading()
    {
        if(isLoading && loadingDialog != null)
        {
            isLoading = false;

            loadingDialog.hide();
            loadingDialog.dismiss();

            loadingDialog = null;
        }
    }

    public boolean isLoading()
    {
        return isLoading;
    }

    // Reachable
    public boolean reachable(Context context)
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
