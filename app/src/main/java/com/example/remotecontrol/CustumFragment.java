package com.example.remotecontrol;

import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.example.remotecontrol.utils.LogUtil;

/**
 * Created by qiaojiange on 2017/3/16.
 */

public class CustumFragment extends Fragment {
    private static final String TAG = "CustumFragment";
    private String Title;

    public CustumFragment(String title) {
        Title = title;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public void onDestroy() {
        LogUtil.d(TAG,"-----onDestroy-----");
        super.onDestroy();
    }
}
