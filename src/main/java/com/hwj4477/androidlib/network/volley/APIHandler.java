package com.hwj4477.androidlib.network.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wjhong
 * @since 2016. 2. 2..
 *
 * API 관리자 클래스 - volley 사용
 *
 */
public class APIHandler {

    /**
     * Instance
     */
    private static final String LOG_TAG = "APIHandler";

    private static APIHandler _handler = null;
    private static RequestQueue requestQueue;
    private static Context _context = null;

    /**
     * singleton
     */
    private APIHandler(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public synchronized static APIHandler getInstance(Context context)
    {
        _context = context;

        if(_handler == null)
        {
            _handler = new APIHandler(context);
        }

        return _handler;
    }

    public void requestGET(String URL, HashMap<String, Object> params, final boolean showProgress, final ResponseListener listener) {

        JsonObjectRequest req = new JsonObjectRequest(URL + "?" + getParamString(params), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if(checkResponse(response)) {

                                listener.success(200, response);
                            }
                            else {

                                listener.failed(404, "error");
                            }
                        }
                        catch (JSONException e) {

                            e.printStackTrace();
                            listener.failed("Json Parse Error");
                        }
                        catch (NullPointerException e) {

                            e.printStackTrace();
                            listener.failed("ResponseListener is Null");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {

                    listener.failed(500, "error");
                }
                catch (NullPointerException e) {

                    e.printStackTrace();
                    listener.failed("ResponseListener is Null");
                }
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return null;
            }
        };

        requestQueue.add(req);
    }

    public void requestPOST(String URL, HashMap<String, Object> params, final boolean showProgress, final ResponseListener listener) {

        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if(checkResponse(response)) {

                                listener.success(200, response);
                            }
                            else {

                                listener.failed(404, "error");
                            }
                        }
                        catch (JSONException e) {

                            e.printStackTrace();
                            listener.failed("Json Parse Error");
                        }
                        catch (NullPointerException e) {

                            e.printStackTrace();
                            listener.failed("ResponseListener is Null");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {

                    listener.failed(500, "error");
                }
                catch (NullPointerException e) {

                    e.printStackTrace();
                    listener.failed("ResponseListener is Null");
                }
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return null;
            }
        };

        requestQueue.add(req);
    }

//    public void requestMultipart(String URL, Map<String, String> params, File file, final boolean showProgress, final ResponseListener listener) {
//
//        MultipartRequest req = new MultipartRequest(URL, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                try {
//
//                    listener.failed(500, "error");
//                }
//                catch (NullPointerException e) {
//
//                    e.printStackTrace();
//                    listener.failed("ResponseListener is Null");
//                }
//            }
//        },
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        JSONObject jsonResponse;
//
//                        try {
//
//                            jsonResponse = new JSONObject(response);
//
//                            if(checkResponse(jsonResponse)) {
//
//                                listener.success(200, response);
//                            }
//                            else {
//
//                                listener.failed(404, "error");
//                            }
//                        }
//                        catch (JSONException e) {
//
//                            e.printStackTrace();
//                            listener.failed("Json Parse Error");
//                        }
//                        catch (NullPointerException e) {
//
//                            e.printStackTrace();
//                            listener.failed("ResponseListener is Null");
//                        }
//                    }
//                }, file,
//                params)
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//
//                return null;
//            }
//        };
//
//        requestQueue.add(req);
//    }

    /**
     * helper
     */
    private String getParamString(HashMap<String, Object> params) {

        String strParam = "";

        if(params == null)
            return strParam;

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());

            strParam += key + "=" + value;

            if(entry != params.entrySet().toArray()[params.entrySet().size()-1])
                strParam += "&";
        }

        return strParam;
    }

    private boolean checkResponse(JSONObject response) {

            return true;
    }
}
