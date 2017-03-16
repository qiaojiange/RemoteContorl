package com.example.remotecontrol.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.remotecontrol.MyApplication;

/**
 * Created by qiaojiange on 2017/3/16.
 */

/****
 * 用于sharedPreference保存数据
 */

public class SPUtil {
    private static SharedPreferences sp = MyApplication.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor = sp.edit();


    public static String getString(String key,String defValue){
       return sp.getString(key,defValue);
    }
    public static void putString(String key,String val){
        editor.putString(key,val);
        editor.commit();
    }

    public static int getInt(String key,int defValue){
        return sp.getInt(key,defValue);
    }
    public static void putInt(String key,int value){
        editor.putInt(key,value);
        editor.commit();
    }


    public static boolean getBoolean(String key,boolean defValue){
        return sp.getBoolean(key,defValue);
    }
    public static void putBoolean(String key,boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }

}
