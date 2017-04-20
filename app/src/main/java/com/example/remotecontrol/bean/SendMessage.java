package com.example.remotecontrol.bean;

import java.util.List;

/**
 * Created by qiaojiange on 2017/4/14.
 */
public class SendMessage {
    /**
     * 设备名称：如相机：camera ，滤波器：lctf ,开发板：fpga等。
     */
    private String deviceName;

    /**
     * 操作：如连接相机，关闭相机等操作
     */
    private int operateId;

    private String device;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getOperateId() {
        return operateId;
    }

    public void setOperateId(int operateId) {
        this.operateId = operateId;
    }



}
