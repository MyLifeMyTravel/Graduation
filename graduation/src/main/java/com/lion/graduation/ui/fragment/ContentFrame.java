package com.lion.graduation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lion.graduation.R;

/**
 * 开启应用显示的界面
 * Created by Lion on 2015/3/13.
 */
public class ContentFrame extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_layout,container,false);
        return view;
    }
}
