package com.example.remotecontrol.bean;

/**
 * Created by qiaojiange on 2017/4/29.
 */

public class Lctf {
    //返回的信息
    private String msg;

    private boolean bConnect;
    private boolean bModeRead;
    private boolean bWorkRead;
    //private boolean

    /**
     * 模式信息
     */
   public static class ModeInfo{
        /**
         * 设备型号
         */
        private String equipType;
        /**
         * 制造商
         */
        private String vendor;
        /**
         * 设备序列号
         */
        private int equipSN;
        /**
         * 制造时间_年
         */
        private int ManDate_y;
        /**
         * 制造日期_月
         */
        private int ManDate_m;
        /**
         * 制造日期_日
         */
        private int ManDate_d;

        /**
         * 固件版本
         */
        int firmwareVersion;

        /**
         * 波长分辨率
         */
        int wavLenRes;

        /**
         * 最小切换时间
         */
        int minSwitchTime;

        public ModeInfo() {
        }

        public String getEquipType() {
            return equipType;
        }

        public void setEquipType(String equipType) {
            this.equipType = equipType;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public int getEquipSN() {
            return equipSN;
        }

        public void setEquipSN(int equipSN) {
            this.equipSN = equipSN;
        }

        public int getManDate_y() {
            return ManDate_y;
        }

        public void setManDate_y(int manDate_y) {
            ManDate_y = manDate_y;
        }

        public int getManDate_m() {
            return ManDate_m;
        }

        public void setManDate_m(int manDate_m) {
            ManDate_m = manDate_m;
        }

        public int getManDate_d() {
            return ManDate_d;
        }

        public void setManDate_d(int manDate_d) {
            ManDate_d = manDate_d;
        }

        public int getFirmwareVersion() {
            return firmwareVersion;
        }

        public void setFirmwareVersion(int firmwareVersion) {
            this.firmwareVersion = firmwareVersion;
        }

        public int getWavLenRes() {
            return wavLenRes;
        }

        public void setWavLenRes(int wavLenRes) {
            this.wavLenRes = wavLenRes;
        }

        public int getMinSwitchTime() {
            return minSwitchTime;
        }

        public void setMinSwitchTime(int minSwitchTime) {
            this.minSwitchTime = minSwitchTime;
        }
    }


    /**
     * 工作参数
     */
   public static class WorkParams{
        /**
         * 起始波长
         */
        private int startWavLen;
        /**
         * 终止波长
         */
        private int endWavLen;
        /**
         * 最小步进波长，单位为nm
         */
        private int minStepWavLen;
        /**
         * 最小切换时间，单位为ms
         */
        private int singleWavWorkTime;
        /**
         * 当前波长
         */
        private int curWavLen;
        /**
         * 控制类型
         */
        private int ctrlType;

        public WorkParams() {
        }

        public int getStartWavLen() {
            return startWavLen;
        }

        public void setStartWavLen(int startWavLen) {
            this.startWavLen = startWavLen;
        }

        public int getEndWavLen() {
            return endWavLen;
        }

        public void setEndWavLen(int endWavLen) {
            this.endWavLen = endWavLen;
        }

        public int getMinStepWavLen() {
            return minStepWavLen;
        }

        public void setMinStepWavLen(int minStepWavLen) {
            this.minStepWavLen = minStepWavLen;
        }

        public int getSingleWavWorkTime() {
            return singleWavWorkTime;
        }

        public void setSingleWavWorkTime(int singleWavWorkTime) {
            this.singleWavWorkTime = singleWavWorkTime;
        }

        public int getCurWavLen() {
            return curWavLen;
        }

        public void setCurWavLen(int curWavLen) {
            this.curWavLen = curWavLen;
        }

        public int getCtrlType() {
            return ctrlType;
        }

        public void setCtrlType(int ctrlType) {
            this.ctrlType = ctrlType;
        }
    }

    /**
     * 定义操作
     */
    public enum  OperateId{

    }
}
