package com.example.xiongpeng.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiongpeng on 2017/9/12.
 */

public class JsonRequestWithAuth<T> extends Request<T> {

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Response.Listener<T> listener;
    private static Map<String, String> mHeader = new HashMap<String, String>();

    static{
        mHeader.put("APP-Key", "LBS-AAA");
        mHeader.put("APP-Secret", "ad12msa234das232in");
    }

    public JsonRequestWithAuth(String url, Class<T> clazz, Response.Listener<T> tag, Map<String, String> appendHeader, Response.Listener<T> listener1) {
        super(Method.GET, url, (Response.ErrorListener) tag);
        this.clazz = clazz;
        this.listener = listener1;
        mHeader.putAll(appendHeader);
    }

    public Map<String, String> getmHeaders() throws AuthFailureError{
        return mHeader;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(jsonStr, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
