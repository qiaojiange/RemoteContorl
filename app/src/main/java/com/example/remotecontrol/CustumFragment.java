package com.example.remotecontrol;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by qiaojiange on 2017/3/16.
 */

public class CustumFragment extends Fragment {
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
}
