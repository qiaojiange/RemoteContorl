package com.example.remotecontrol;

import android.content.DialogInterface;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.remotecontrol.bean.SendMessage;
import com.example.remotecontrol.utils.LogUtil;

/**
 * Created by qiaojiange on 2017/3/16.
 */

public class LctfFragment extends CustumFragment implements View.OnClickListener{
    private static final String TAG = "LctfFragment";
    /**
     * 刷新端口
     */
    private Button btRefreshPorts;
    /**
     * 打开端口
     */
    private Button btOpenPort;
    /**
     * 关闭端口
     */
    private Button btClosePort;
    /**
     * 端口
     */
    private Spinner spPorts;


    /**
     * 起始波长
     */
    private EditText etStartWavLen;

    /**
     * 终止波长
     */
    private EditText etEndWavLen;

    /**
     * 最小步进波长
     */
    private EditText etMinStepWavLen;

    /**
     * 最小切换时间
     */
    private EditText etMinSwitchTime;

    /**
     * 当前波长
     */
    private EditText etCurrWavLen;


    /**
     * 单组工作、 连续工作
     */
    private Spinner spContinuesWork;

    /**
     *超温后强制工作、超温后停止工作
     */
    private Spinner spForceWork;

    /**
     * 工作模式：同步，异步
     */
    private RadioButton rbWorkParter;

    /**
     *读取内部参数
     */
    private Button btReadInterParameters;

    /**
     *写入内部参数
     */
    private Button btWriteInterParameters;

    /**
     *开始工作
     */
    private Button btStartWork;

    /**
     *停止工作
     */
    private Button btStopWork;


    public LctfFragment(String title) {
        super(title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lctf_layout,container,false);

        btRefreshPorts = (Button)view.findViewById(R.id.freshSerialPort);
        btOpenPort = (Button)view.findViewById(R.id.openSerialPort);
        btClosePort = (Button)view.findViewById(R.id.closeSerialPort);
        spPorts = (Spinner)view.findViewById(R.id.serialPorts);

        etStartWavLen = (EditText)view.findViewById(R.id.stareWavelength);
        etEndWavLen = (EditText)view.findViewById(R.id.endWavelength);
        etMinStepWavLen = (EditText)view.findViewById(R.id.minStepWavelength);
        etMinSwitchTime = (EditText)view.findViewById(R.id.minSwitchTime);
        etCurrWavLen = (EditText)view.findViewById(R.id.currentWavelength);


        btReadInterParameters = (Button)view.findViewById(R.id.readInterParams);
        btWriteInterParameters = (Button)view.findViewById(R.id.writeInterParams);
        btStartWork = (Button)view.findViewById(R.id.startWork);
        btStopWork = (Button)view.findViewById(R.id.stopWork);



//        绑定控件
        spContinuesWork = (Spinner) view.findViewById(R.id.continueWork);
        spForceWork = (Spinner)view.findViewById(R.id.forceWork);


//        绑定监听器
        btRefreshPorts.setOnClickListener(this);
        btOpenPort.setOnClickListener(this);
        btClosePort.setOnClickListener(this);

        btReadInterParameters.setOnClickListener(this);
        btWriteInterParameters.setOnClickListener(this);
        btStartWork.setOnClickListener(this);
        btStopWork.setOnClickListener(this);


        spContinuesWork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setTextColor(Color.WHITE);
                tv.setGravity(Gravity.CENTER);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spForceWork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setTextColor(Color.WHITE);
                tv.setGravity(Gravity.CENTER);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }


    @Override
    public void onClick(View v) {
     int id = v.getId();
        switch (id){

            case R.id.freshSerialPort:
                LogUtil.d(TAG,"----R.id.freshSerialPort---");
                freshSerialPort();
                break;
            case R.id.openSerialPort:
                LogUtil.d(TAG,"----R.id.openSerialPort---");

                break;

            case R.id.closeSerialPort:
                LogUtil.d(TAG,"----R.id.closeSerialPort---");


                break;

            case R.id.readInterParams:
                LogUtil.d(TAG,"----R.id.readInterParams---");

                break;


            case R.id.writeInterParams:
                LogUtil.d(TAG,"----R.id.writeInterParams---");

                break;

            case R.id.startWork:
                LogUtil.d(TAG,"----R.id.startWork---");


                break;

            case R.id.stopWork:
                LogUtil.d(TAG,"----R.id.stopWork:---");

                break;


        }
    }

    private void freshSerialPort() {
        SendMessage message = new SendMessage();
        message.setDeviceName("");

    }
}
