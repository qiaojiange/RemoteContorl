package com.example.remotecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qiaojiange on 2017/3/16.
 */

public class ShowPictureFragment extends CustumFragment {

    public ShowPictureFragment(String title) {
        super(title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_picture_layout,container,false);
        return view;
    }

}
