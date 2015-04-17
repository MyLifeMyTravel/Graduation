package com.lion.graduation2.ui.fragement;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lion.graduation2.util.Constant;

/**
 * 用户信息显示
 * Created by Lion on 2015/3/20.
 */
public class UserFragment extends BaseFragment {

    private static final String TITLE = "用户信息";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTitleListener().setTitle(TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.task_devices_item, container, false);
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDispalyHomeListener().showDisplayHome(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constant.TAG, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(Constant.TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(Constant.TAG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Constant.TAG, "onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constant.TAG, "onDestroy()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(Constant.TAG, "onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(Constant.TAG, "onDetach()");
        getDispalyHomeListener().showDisplayHome(true);
    }
}
