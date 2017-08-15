package com.example.remotecontrol.utils;

import android.util.Log;

import java.util.logging.Level;

/**
 * Created by qiaojiange on 2017/3/16.
 */

public class LogUtil {


    private static final int Verbose = 1;
    private static final int Debug = 2;
    private static final int Info = 3;
    private static final int Warn = 4;
    private static final int Error = 5;


    private static final int Level = 6;
    public static void v(String Tag,String info){
        if(Level>Verbose){
            Log.v(Tag,info);
        }
    }

    public static void e(String Tag,String info){
        if(Level>Error){
            Log.e(Tag,info);
        }
    }

    public static void d(String Tag,String info){
        if(Level>Debug){
            Log.d(Tag,info);
        }
    }

    public static void i(String Tag,String info){
        if(Level>Info){
            Log.i(Tag,info);
        }
    }
    public static void w(String Tag,String info){
        if(Level>Warn){
            Log.w(Tag,info);
        }
    }

}
