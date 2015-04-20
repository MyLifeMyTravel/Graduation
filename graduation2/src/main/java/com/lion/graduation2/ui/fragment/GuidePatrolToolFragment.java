package com.lion.graduation2.ui.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.lion.graduation2.R;
import com.lion.graduation2.listener.OnTitleSetListener;
import com.lion.graduation2.ui.adapter.ListTableAdapter;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lion on 2015/4/20.
 */
public class GuidePatrolToolFragment extends Fragment {

    /* guide目录的文件路径 */
    private String URL_GUIDE_FILE;
    private String title;
    /* ListView */
    private ListView listView = null;
    /* 从服务器返回的结果 */
    private String result = null;
    /* 名称、单位、数量、备注 */
    private List<String> names, units, counts, notes;
    /* 标题 */
    private static final String TITLE = "巡检指导";
    private OnTitleSetListener onTitleSetListener;
    /* ProgressDialog */
    private ProgressDialog dialog = null;
    /* 加载提示 */
    private static final String LOADING = "正在加载，请稍后...";

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
        View view = inflater.inflate(R.layout.fragment_guide_patrol_tool, container, false);
        listView = (ListView) view.findViewById(R.id.lv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFile();
    }

    private void getFile() {
        FinalHttp fh = new FinalHttp();
        fh.get(URL_GUIDE_FILE, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                dialog.dismiss();
                initData((String) o);
                setListData();
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog = ProgressDialog.show(getActivity(), null, LOADING);
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                super.onFailure(t, strMsg);
                Toast.makeText(getActivity(), "获取列表失败,请刷新重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData(String result) {
        names = new ArrayList<>();
        units = new ArrayList<>();
        counts = new ArrayList<>();
        notes = new ArrayList<>();
        String[] lines = result.split("\n");
        for (String line : lines) {
            names.add(line.split(":")[0].trim());
            units.add(line.split(":")[1].trim());
            counts.add(line.split(":")[2].trim());
            notes.add(line.split(":")[3].trim());
        }
    }

    private void setListData() {
        String[] from = {"name", "unit", "count", "notes"};
        int[] to = {R.id.name, R.id.unit, R.id.count, R.id.note};
        List<Map<String, String>> lists = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put(from[0], names.get(i));
            map.put(from[1], units.get(i));
            map.put(from[2], counts.get(i));
            map.put(from[3], notes.get(i));
            lists.add(map);
        }
        listView.setAdapter(new ListTableAdapter(getActivity(), lists, R.layout.fragment_guide_patrol_tool, from, to));
    }
}
