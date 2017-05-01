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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.remotecontrol.bean.Lctf;
import com.example.remotecontrol.bean.RecvMessage;
import com.example.remotecontrol.bean.SendMessage;
import com.example.remotecontrol.model.LctfDao;
import com.example.remotecontrol.utils.HttpUtil;
import com.example.remotecontrol.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

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


    private LctfDao lctfDao = new LctfDao();

    public LctfFragment(String title) {
        super(title);
    }

    /**
     *保存端口
     */
    private String port;

    private TextView tvStatus;

    //    类型
    private static MediaType mediaType = MediaType.parse("application/json;charset=utf-8");

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

        //状态信息
        tvStatus = (TextView)view.findViewById(R.id.status);

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

        /**
         * 获取串口的数据
         */
        spPorts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setTextColor(Color.WHITE);
                LogUtil.d(TAG,"----spPorts-------"+tv.getText().toString());
                port = tv.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                openSerialPort();
                break;

            case R.id.closeSerialPort:
                LogUtil.d(TAG,"----R.id.closeSerialPort---");
                closeSerialPort();
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


//关闭串口
    private void closeSerialPort() {
        RequestBody body = RequestBody.create(mediaType,lctfDao.jsonCloseSerialPort());
        HttpUtil.post(body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void openSerialPort() {
        if (port == null || port.length() == 0){
            return ;
        }

        RequestBody body = RequestBody.create(mediaType,lctfDao.jsonOpenSerialPort(port));
        HttpUtil.post(body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strLen = response.header(HttpUtil.CONTENT_LENGTH);

                byte[] data = response.peekBody(Integer.parseInt(strLen)).bytes();
                String strTemp = new String(data,"utf-8");

                final RecvMessage message = LctfDao.gson.fromJson(strTemp,RecvMessage.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(message.getStatus() ==Lctf.LctfStatus.LCTF_STATUS_ERROR.ordinal() ){
                            tvStatus.setText("状态信息：\n\t"+message.getDescribe());
                        }else{
                            tvStatus.setText("状态信息：\n\t"+message.getDescribe());
                        }

                    }
                });


            }
        });
    }


//刷新串口
    private void freshSerialPort() {

        RequestBody body = RequestBody.create(mediaType,lctfDao.jsonFreshSerialPort());
        HttpUtil.post(body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                String strLen = response.header("Content-Length");
//                LogUtil.d(TAG,"--strlen-----"+strLen);
//                byte[] data = response.peekBody(Integer.parseInt(strLen)).bytes();
//
//                String str = new String(data,"utf-8");
//                LogUtil.d(TAG,"--------"+str);

//                String str = new String(response.peekBody(Integer.parseInt(strLen)).bytes(), "utf-8");
//                LogUtil.d(TAG, "---" + str);

                String strLen = response.header("Content-Length");
                LogUtil.d(TAG,"--strlen-----"+strLen);
                String str = new String(response.peekBody(Integer.parseInt(strLen)).bytes(), "utf-8");
                LogUtil.d(TAG, "---" + str);

                RecvMessage recvMessage = LctfDao.gson.fromJson(str,RecvMessage.class);

                String strParams = recvMessage.getParams();
               // LctfDao.gson.

                LogUtil.d(TAG,"---------"+strParams);
                Lctf.Port port = LctfDao.gson.fromJson(strParams,Lctf.Port.class);

                final String[] ports = port.getPort().toArray(new String[0]);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,ports);

                        spPorts.setAdapter(adapter);
                    }
                });

            }
        });

    }
}
