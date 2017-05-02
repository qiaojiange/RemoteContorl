package com.example.remotecontrol.model;

import com.example.remotecontrol.bean.Lctf;
import com.example.remotecontrol.bean.SendMessage;
import com.google.gson.Gson;

import okhttp3.MediaType;

/**
 * Created by qiaojiange on 2017/4/29.
 */

public class LctfDao {
//    设备名称
    private static  String DeviceName="lctf";
//    静态json数据解析
    public static Gson gson = new Gson();

    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public String jsonFreshSerialPort(){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Lctf.OperateId.LCTF_FRESH_SERIAL_PORT.getVal());
        return gson.toJson(message);
    }



    public String jsonOpenSerialPort(String port){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Lctf.OperateId.LCTF_OPEN_SERIAL_PORT.getVal());
        message.setParams(port);
        return gson.toJson(message);
    }

    public String jsonCloseSerialPort(){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Lctf.OperateId.LCTF_CLOSE_SERIAL_PORT.getVal());

        return gson.toJson(message);

    }

    public String jsonReadInterParams(){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Lctf.OperateId.LCTF_READ_INTER_PARAMS.getVal());
        return gson.toJson(message);

    }

    public String jsonReadBasicInfo(){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Lctf.OperateId.LCTF_READ_BASIC_INFO.getVal());
        return gson.toJson(message);

    }

    public String jsonReadTempInfo(){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Lctf.OperateId.LCTF_READ_TEMP_INFO.getVal());
        return gson.toJson(message);
    }

    public String jsonWriteInterParams(String params){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Lctf.OperateId.LCTF_WRITE_INTER_PARAMS.getVal());
        message.setParams(params);
        return gson.toJson(message);

    }



    public String jsonStartWork(int sync){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Lctf.OperateId.LCTF_START_WORK.getVal());
        message.setParams(""+sync);
        return gson.toJson(message);
    }


    public String jsonStopWork(int sync){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Lctf.OperateId.LCTF_STOP_WORK.getVal());
        message.setParams(""+sync);
        return gson.toJson(message);

    }



}
