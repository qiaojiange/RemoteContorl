package com.example.remotecontrol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.remotecontrol.bean.Camera;
import com.example.remotecontrol.bean.RecvMessage;
import com.example.remotecontrol.model.CameraDao;
import com.example.remotecontrol.utils.HttpUtil;
import com.example.remotecontrol.utils.LogUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by qiaojiange on 2017/3/16.
 */

public class CameraFragment extends CustumFragment implements View.OnClickListener {
    private static final String TAG = "CameraFragment";

    private Button btConnect;
    private Button btDisconnect;
    private Button btRefresh;
    private Button btStartPreview;
    private Button btStopPreview;
    private Button btTransPicture;

    private Button btUpdate;
    private Button btSaveImage;

    private TextView tvExposure;
    private TextView tvGain;
    private TextView tvWidth;
    private TextView tvHeight;
    private TextView tvXOffset;
    private TextView tvYOffset;

    private ImageView imageView;
    //选择图片的位数
    private RadioGroup rg;


    private Gson gson = new Gson();

    //    类型
    private static MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
    ;


    public CameraFragment(String title) {
        super(title);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.camera_layout, container, false);

        btConnect = (Button) view.findViewById(R.id.connect);
        btDisconnect = (Button) view.findViewById(R.id.disconnect);
        btRefresh = (Button) view.findViewById(R.id.refresh);
        btStartPreview = (Button) view.findViewById(R.id.start_preview);
        btUpdate = (Button) view.findViewById(R.id.update);
        btSaveImage = (Button) view.findViewById(R.id.save_image);

        btStopPreview = (Button) view.findViewById(R.id.stop_preview);
        btTransPicture = (Button) view.findViewById(R.id.trans_picture);


        tvExposure = (TextView) view.findViewById(R.id.exposure);
        tvGain = (TextView) view.findViewById(R.id.gain);
        tvWidth = (TextView) view.findViewById(R.id.width);
        tvHeight = (TextView) view.findViewById(R.id.height);
        tvXOffset = (TextView) view.findViewById(R.id.xOffset);
        tvYOffset = (TextView) view.findViewById(R.id.yOffset);
        rg = (RadioGroup) view.findViewById(R.id.rg);
        imageView = (ImageView) view.findViewById(R.id.image);


        btConnect.setOnClickListener(this);
        btDisconnect.setOnClickListener(this);
        btRefresh.setOnClickListener(this);
        btStartPreview.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btSaveImage.setOnClickListener(this);

        btStopPreview.setOnClickListener(this);
        btTransPicture.setOnClickListener(this);


        rg.check(R.id.bit8);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        tvExposure.setEnabled(false);
//        tvGain.setEnabled(false);
//        tvWidth.setEnabled(false);
//        tvHeight.setEnabled(false);
//        tvXOffset.setEnabled(false);
//        tvYOffset.setEnabled(false);


    }

    private boolean isPriview = false;

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.connect:
//                Toast.makeText(getActivity(),"connect",Toast.LENGTH_SHORT).show();
                LogUtil.d(TAG, "---connect--");
                connect();
                break;
            case R.id.disconnect:
                LogUtil.d(TAG, "---disconnect--");
                disconnect();
                break;

            //获取相机内部参数
            case R.id.refresh:
                LogUtil.d(TAG, "---disconnect--");
                getParameter();
                break;

//            预览，由相机回传回来的数据决定
            case R.id.start_preview:
                LogUtil.d(TAG, "---start_preview--");
              //  if (!isPriview) {
                    startPreveiw();
                //}
                break;

            case R.id.stop_preview:
                LogUtil.d(TAG, "--stop_preview--");
//                if(isPriview){
                stopPreview();
//                }
                break;

            case R.id.trans_picture:
                LogUtil.d(TAG, "--trans_picture--");
               // if (isPriview) {
                    transPicture();
//                }
                break;


            case R.id.update:
                LogUtil.d(TAG, "---Update--");
                setParameter();
                break;
            case R.id.save_image:
                LogUtil.d(TAG, "---save_image--");

                break;

            default:
                break;
        }


    }

    //断开连接
    private void disconnect() {
        final RequestBody requestBody = RequestBody.create(mediaType, CameraDao.jsonDisconnect());
        HttpUtil.post(requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strLen = response.header("Content-Length");

//                String str = response.body().toString();
                byte[] date = response.peekBody(Integer.parseInt(strLen)).bytes();
                String str = new String(date, "utf-8");
                LogUtil.d(TAG, "--------" + str);
                final RecvMessage recvMessage = gson.fromJson(str, RecvMessage.class);

                LogUtil.d(TAG, "---" + recvMessage.getDescribe());

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), recvMessage.getDescribe(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }


    private void connect() {

//        String str = CameraDao.jsonConnect();
//        Log.d(TAG,"----"+str);

        RequestBody requestBody = RequestBody.create(mediaType, CameraDao.jsonConnect());
        HttpUtil.post(requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strLen = response.header("Content-Length");

                String str = new String(response.peekBody(Integer.parseInt(strLen)).bytes(), "utf-8");
                LogUtil.d(TAG, "--------" + str);
                final RecvMessage recvMessage = gson.fromJson(str, RecvMessage.class);

                LogUtil.d(TAG, "---" + recvMessage.getDescribe());


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (recvMessage.getStatus() == Camera.CameraStatus.CAMERA_STATUS_OK.ordinal()) {

                            Toast.makeText(getActivity(), "connect success!", Toast.LENGTH_SHORT).show();
//                            连接成功就要调用刷新参数的功能，否则预览之后直接保存成jpg,会导致服务器端的相机获取视频流函数执行崩溃。
                            getParameter();
                        } else {

                            Toast.makeText(getActivity(), "connect failure!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }


    private void getParameter() {
        LogUtil.d(TAG,"---getParameter--");
        RequestBody requestBody = RequestBody.create(mediaType, CameraDao.jsonGetParameter());
        HttpUtil.post(requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strLen = response.header("Content-Length");

                String str = new String(response.peekBody(Integer.parseInt(strLen)).bytes(), "utf-8");
                LogUtil.d(TAG, "--------" + str);
                final RecvMessage recvMessage = gson.fromJson(str, RecvMessage.class);


//                showCameraInfo(camera);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (recvMessage.getStatus() == Camera.CameraStatus.CAMERA_STATUS_OK.ordinal()) {
                            String device = recvMessage.getDevice();
                            Camera camera = gson.fromJson(device, Camera.class);

                            String strTemp = String.format("%.2f", camera.getExposure());
//                            LogUtil.d(TAG,"------strTemp:"+strTemp);
//                            String strTemp = String.format("%.2f", Locale.getDefault());
                            tvExposure.setText(strTemp);
                            tvGain.setText("" + camera.getGain());
                            tvYOffset.setText("" + camera.getyOffset());
                            tvXOffset.setText("" + camera.getxOffset());
                            tvWidth.setText("" + camera.getWidth());
                            tvHeight.setText("" + camera.getHeigh());

                        }
                        LogUtil.d(TAG, "---" + recvMessage.getDescribe());
                        Toast.makeText(getActivity(),recvMessage.getDescribe(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }


    private void setParameter() {
        Camera camera = new Camera();
        String strExposure = tvExposure.getText().toString();
        if (TextUtils.isEmpty(strExposure)){
            return;
        }

        String strGain = tvGain.getText().toString();
        if (TextUtils.isEmpty(strExposure)){
            return;
        }

        String strHeight = tvHeight.getText().toString();
        if (TextUtils.isEmpty(strExposure)){
            return;
        }

        String strWidth = tvWidth.getText().toString();
        if (TextUtils.isEmpty(strWidth)){
            return;
        }

        String strXOffset = tvXOffset.getText().toString();
        if (TextUtils.isEmpty(strXOffset)){
            return;
        }

        String strYOffset = tvYOffset.getText().toString();
        if (TextUtils.isEmpty(strYOffset)){
            return;
        }


        camera.setExposure(Float.parseFloat(strExposure));
        camera.setGain(Float.parseFloat(strGain));
        camera.setHeigh(Integer.parseInt(strHeight));
        camera.setWidth(Integer.parseInt(strWidth));
        camera.setxOffset(Integer.parseInt(strXOffset));
        camera.setyOffset(Integer.parseInt(strYOffset));


        if (rg.getCheckedRadioButtonId() == R.id.bit8) {
            camera.setIs8Bit(0);//8位图片
        } else {
            camera.setIs8Bit(1);//16位图片
        }

        RequestBody requestBody = RequestBody.create(mediaType, CameraDao.jsonSetParameter(camera));

        HttpUtil.post(requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strLen = response.header("Content-Length");

                String str = new String(response.peekBody(Integer.parseInt(strLen)).bytes(), "utf-8");
                LogUtil.d(TAG, "---" + str);
                final RecvMessage message = gson.fromJson(str, RecvMessage.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (message.getStatus() == Camera.CameraStatus.CAMERA_STATUS_OK.ordinal()) {
                            Toast.makeText(getActivity(), message.getDescribe(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), message.getDescribe(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

//    private PreviewThread previewThread = null;


    //    先写第一步，开始和启动预览，之后的两步都直接使用服务进行更新
    private void startPreveiw() {
//        1.开启预览 2.保存jpg图片，3.传输图片，更新界面（这个地方要用到sevice) 这个地方是个难点。
        RequestBody requestBody = RequestBody.create(mediaType, CameraDao.jsonStartPreview());

        HttpUtil.post(requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strLen = response.header("Content-Length");

                String str = new String(response.peekBody(Integer.parseInt(strLen)).bytes(), "utf-8");
                LogUtil.d(TAG, "---" + str);
                final RecvMessage message = gson.fromJson(str, RecvMessage.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (message.getStatus() == Camera.CameraStatus.CAMERA_STATUS_OK.ordinal()) {
                        }
                        Toast.makeText(getActivity(), message.getDescribe(), Toast.LENGTH_SHORT).show();
                    }
                });

//                if (message.getStatus() == Camera.CameraStatus.CAMERA_STATUS_OK.ordinal()){
////                    if(null == previewThread){
////                        previewThread = new PreviewThread();
////                    }
////                    previewThread.start();
//                }

            }
        });
    }

    private void stopPreview() {
//        1.停止传输图片服务 2.关闭相机预览
//        if (previewThread!=null){
//            previewThread.setGoon(false);
//        }

        LogUtil.d(TAG, "------" + CameraDao.jsonStopPreview());
        RequestBody requestBody = RequestBody.create(mediaType, CameraDao.jsonStopPreview());

        HttpUtil.post(requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strLen = response.header("Content-Length");

                String str = new String(response.peekBody(Integer.parseInt(strLen)).bytes(), "utf-8");
                LogUtil.d(TAG, "---" + str);

                final RecvMessage recvMessage = gson.fromJson(str, RecvMessage.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (recvMessage.getStatus() == Camera.CameraStatus.CAMERA_STATUS_OK.ordinal()) {
                            isPriview = false;
                        }
                        Toast.makeText(getActivity(), recvMessage.getDescribe(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    private String jsonTransPicture = CameraDao.jsonTransPicture();

    //    传输图片
    public void transPicture() {
        LogUtil.d(TAG,"---transPicture()---");
        TransPictureTask transPictureTask = new TransPictureTask();
        transPictureTask.execute(jsonTransPicture);
    }


//    private class PreviewThread extends Thread {
//
//        private  boolean goon = true;
//
//        public void setGoon(boolean goon) {
//            this.goon = goon;
//        }
//        private  TransPictureTask transPictureTask = new TransPictureTask();
//        private String json = CameraDao.jsonTransPicture();
//
//        @Override
//        public void run() {
//            //super.run();
//            while (goon) {
//
//                transPictureTask.execute(json);
//                try {
//                    sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }

    private class TransPictureTask extends AsyncTask<String, Void, byte[]> {

        byte[] bytes = null;

        @Override
        protected byte[] doInBackground(String... params) {
            LogUtil.d(TAG, "----TransPictureTask--------doInBackground---------");
            LogUtil.d(TAG, "----TransPictureTask-----"+params[0]);

            RequestBody requestBody = RequestBody.create(mediaType, params[0]);

            Request.Builder builder = new Request.Builder();
            builder.post(requestBody).url(HttpUtil.url_pref);
            Response response = null;

            try {
                response = HttpUtil.client.newCall(builder.build()).execute();
//            System.out.println(response.body().toString());
                String strContentType = response.header("Content-Type");
                String strLen = response.header("Content-Length");
                LogUtil.d(TAG,"---strLen------"+strLen);

                if (strContentType.equals("picture")){
                    LogUtil.d(TAG,"picture coming!");
                    bytes = response.peekBody(Integer.parseInt(strLen)).bytes();
                }else{
                    bytes = response.peekBody(Integer.parseInt(strLen)).bytes();
                    String string = new String(bytes,"utf-8");
                    Log.d(TAG, "doInBackground: "+string);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
//            if (response != null){
//                response.close();
//            }
            }


            return bytes;
        }

        @Override
        protected void onPostExecute(byte[] date) {

            super.onPostExecute(date);
            if (date != null && date.length != 0) {
                Glide.with(getActivity()).load(date).into(imageView);
                isPriview = true;
            }

        }
    }
}
