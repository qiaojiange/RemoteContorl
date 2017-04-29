package com.example.remotecontrol.model;

import com.example.remotecontrol.bean.SendMessage;
import com.google.gson.Gson;

import okhttp3.MediaType;

/**
 * Created by qiaojiange on 2017/4/29.
 */

public class LctfDao {
    private static  String DeviceName="lctf";
    private static Gson gson = new Gson();

    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public String jsonFreshSerialPort(){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId();
        return gson.toJson(message);
    }

}
