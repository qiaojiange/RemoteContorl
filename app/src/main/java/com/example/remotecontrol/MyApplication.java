package com.example.remotecontrol;

import android.app.Application;
import android.content.Context;

/**
 * Created by qiaojiange on 2017/3/16.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public  static Context getContext(){
        return context;
    }

}
