package com.example.remotecontrol.utils;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.RequestBody;

/**
 * Created by qiaojiange on 2017/3/16.
 */

public class HttpUtil {
    private static final String TAG = "HttpUtil";
//    public static  String ipAddress = "10.0.2.2";
//    private static String port = "8888";
    public static String url_pref = "http://10.0.2.2:8888/reg?";
//    public  String url_pref = "http://"+ipAddress+":"+port+"/reg?";
    public static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS).build();

    public static final String CONTENT_LENGTH = "Content-Length";


//    这个地方没有测试过
    public static void post(RequestBody body,Callback callback){
      //  Log.d(TAG, "postMessage: -------");
        Request request = new Request.Builder().url(url_pref).post(body).build();
        client.newCall(request).enqueue(callback);

    }

//    public static void post(Request request,Callback callback){
//
//    }

}
