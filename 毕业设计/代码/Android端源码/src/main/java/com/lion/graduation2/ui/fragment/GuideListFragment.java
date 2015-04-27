package com.lion.graduation2.ui.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.graduation2.R;
import com.lion.graduation2.listener.OnTitleSetListener;
import com.lion.graduation2.util.HttpUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lion on 2015/4/20.
 */
public class GuideListFragment extends Fragment {

    /* 服务器返回的list.txt的文件内容 */
    private String fileList = null;
    /* guide目录下文件的中文名 */
    private List<String> fileName = new ArrayList<>();
    /* guide目录下存在的文件真实名称 */
    private List<String> file = new ArrayList<>();
    /* 获取文件列表的php文件URL */
    private static final String URL_FILELIST = HttpUtils.HttpUrl.DOMAIN_URL + "getFileList.php";
    /* guide目录的路径 */
    private static final String URL_GUIDE_FILE = HttpUtils.HttpUrl.DOMAIN_URL + "guide/";
    /* guide list 显示的ListView */
    private ListView listView = null;
    /* ArrayAdapter适配器 */
    private ArrayAdapter<String> adapter = null;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_list, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, fileName);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                bundle.putString("url", URL_GUIDE_FILE + file.get(position));
                bundle.putString("title", fileName.get(position));
                if (position == 0 || position == 2 || position == 3) {
                    fragment = new ReadGuide_01Fragment();
                } else if (position == 1) {
                    fragment = new GuidePatrolToolFragment();
                }
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).addToBackStack(null).commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //当fileName为初始状态时获取数据
        if (fileName.size() == 0) {
            getFileList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onTitleSetListener.setTitle(TITLE);
    }

    private void getFileList() {
        FinalHttp fh = new FinalHttp();
        fh.get(URL_FILELIST, new AjaxCallBack<Object>() {

            @Override
            public void onStart() {
                super.onStart();
                dialog = ProgressDialog.show(getActivity(), null, LOADING);
            }

            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                dialog.dismiss();
                fileList = (String) o;
                String[] lines = fileList.split("\n");
                for (String line : lines) {
                    fileName.add(line.split(":")[0].trim());
                    file.add(line.split(":")[1].trim());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                super.onFailure(t, strMsg);
                Toast.makeText(getActivity(), "获取列表失败:" + strMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
