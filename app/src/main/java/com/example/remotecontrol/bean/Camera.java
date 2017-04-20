package com.example.remotecontrol.bean;

/**
 * Created by qiaojiange on 2017/4/14.
 */

public class Camera {

    private boolean bPriviewing;//预览

    private int width;
    private int heigh;

    private boolean bConnected;//连接

    private float exposure;
    private float gain;

    private int xOffset;
    private int yOffset;

    private int is8Bit;

    public boolean isbPriviewing() {
        return bPriviewing;
    }

    public void setbPriviewing(boolean bPriviewing) {
        this.bPriviewing = bPriviewing;
    }

    public boolean isbConnected() {
        return bConnected;
    }

    public void setbConnected(boolean bConnected) {
        this.bConnected = bConnected;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public long getHeigh() {
        return heigh;
    }

    public void setHeigh(int heigh) {
        this.heigh = heigh;
    }

    public float getExposure() {
        return exposure;
    }

    public void setExposure(float exposure) {
        this.exposure = exposure;
    }

    public float getGain() {
        return gain;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getIs8Bit() {
        return is8Bit;
    }

    public void setIs8Bit(int is8Bit) {
        this.is8Bit = is8Bit;
    }

    public enum  OperateId{
//      连接
        CAMERA_CONNECT(0),

//      断开连接
        CAMERA_DISCONNECT(1),

//        保存图片
        CAMERA_SAVE_PICTURE16(2),

//        获取参数
        CAMERA_GET_PARAMETER(3),

//        设置参数
        CAMERA_SET_PARAMETER(4),

//        开始预览
        CAMERA_START_PREVIEW(5),

//        停止预览
        CAMERA_STOP_PREVIEW(6),

//        传输图片
        CAMERA_TRANS_PICTURE(7);

        private int  val;

        OperateId(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }


//  相机状态
    public enum CameraStatus{
        CAMERA_STATUS_ERROR,CAMERA_STATUS_OK
    }

}
