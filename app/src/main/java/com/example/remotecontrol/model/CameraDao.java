package com.example.remotecontrol.model;

import com.example.remotecontrol.bean.Camera;
import com.example.remotecontrol.bean.SendMessage;
import com.example.remotecontrol.utils.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by qiaojiange on 2017/4/19.
 */

public class CameraDao {
    private Camera camera;
    private static  String DeviceName="camera";
    private static Gson gson = new Gson();

    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    public CameraDao(Camera camera) {
        this.camera = camera;

    }




    public static String jsonConnect(){
        SendMessage message = new SendMessage();
        message.setDeviceName(DeviceName);
        message.setOperateId(Camera.OperateId.CAMERA_CONNECT.getVal());
        return gson.toJson(message);
    }

    public static String jsonDisconnect(){
        SendMessage message = new SendMessage();
        message.setDeviceName("camera");
        message.setOperateId(Camera.OperateId.CAMERA_DISCONNECT.getVal());

        return gson.toJson(message);
    }

    public static String jsonGetParameter(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setDeviceName("camera");
        sendMessage.setOperateId(Camera.OperateId.CAMERA_GET_PARAMETER.getVal());

        return gson.toJson(sendMessage);
    }
    public static   String jsonSetParameter(Camera camera){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setDeviceName("camera");
        sendMessage.setOperateId(Camera.OperateId.CAMERA_SET_PARAMETER.getVal());

        String strCamera = gson.toJson(camera);
        sendMessage.setDevice(strCamera);

        return gson.toJson(sendMessage);
    }
    public static String jsonStartPreview() {
        SendMessage message = new SendMessage();
        message.setDeviceName("camera");
        message.setOperateId(Camera.OperateId.CAMERA_START_PREVIEW.getVal());

        return gson.toJson(message);
    }

    public static  String jsonStopPreview() {
        SendMessage message = new SendMessage();
        message.setDeviceName("camera");
        message.setOperateId(Camera.OperateId.CAMERA_STOP_PREVIEW.getVal());

        return gson.toJson(message);

    }

    public static String jsonTransPicture() {
        SendMessage message = new SendMessage();
        message.setDeviceName("camera");
        message.setOperateId(Camera.OperateId.CAMERA_TRANS_PICTURE.getVal());

        return  gson.toJson(message);
    }



}
