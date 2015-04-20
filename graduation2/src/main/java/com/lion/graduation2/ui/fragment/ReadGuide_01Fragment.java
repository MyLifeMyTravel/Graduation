package com.lion.graduation2.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.graduation2.R;
import com.lion.graduation2.listener.OnTitleSetListener;
import com.lion.graduation2.util.HttpUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

/**
 * Created by Lion on 2015/4/20.
 */
public class ReadGuide_01Fragment extends Fragment {

    private TextView showText;
    /* guide目录的文件路径 */
    private String URL_GUIDE_FILE;
    private String title;
    private OnTitleSetListener onTitleSetListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.onTitleSetListener = (OnTitleSetListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnDispalyHomeListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        URL_GUIDE_FILE = bundle.getString("url");
        title = bundle.getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_guide_01, container, false);
        showText = (TextView) view.findViewById(R.id.showText);
        //设置滚动条
        showText.setMovementMethod(ScrollingMovementMethod.getInstance());
        //设置标题
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onTitleSetListener.setTitle(title);
        getFile();
    }

    private void getFile() {
        FinalHttp fh = new FinalHttp();
        fh.get(URL_GUIDE_FILE, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                showText.setText((String) o);
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                super.onFailure(t, strMsg);
            }
        });
    }
}
