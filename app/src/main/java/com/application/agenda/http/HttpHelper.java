package com.application.agenda.http;

import android.os.StrictMode;
import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpHelper {

    static {
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.
                        Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public static final String URL_BASE = "http://192.168.15.3:3000"; // IP do Ubuntu
    //public static final String URL_BASE = "http://192.168.187.231:3000";

    public String get(String url){
        String responseString = "";
        final String URL = URL_BASE + url;
        final MediaType headerHttp = MediaType.parse("application/json; charset=UTF-8");
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL).get().build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if ( responseBody != null ){
                responseString = responseBody.string();
                Log.d("debug-mode", responseString);
            }
        }catch (Exception e){
            Log.d("debug-mode", e.toString());
            responseString = e.toString();
        }
        return responseString;
    }

    public String post(String url, String json){
        String responseString = "";
        final String URL = URL_BASE + url;
        final MediaType headerHttp = MediaType.parse("application/json; charset=UTF-8");
        final OkHttpClient client = new OkHttpClient();
        final RequestBody body = RequestBody.create(headerHttp, json);
        Request request = new Request.Builder().url(URL).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            Log.d("debug-mode", "" + responseBody);
            if ( responseBody != null ){
                responseString = responseBody.string();
                Log.d("debug-mode", responseString);
            }
        }catch (Exception e){
            Log.d("debug-mode", e.toString());
            responseString = e.toString();
        }
        return responseString;
    }
}
