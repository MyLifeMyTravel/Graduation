package com.lion.graduation2.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.HistoryBean;
import com.lion.graduation2.bean.json.ProblemBean;
import com.lion.graduation2.bean.json.XsbzBean;
import com.lion.graduation2.ui.DividerItemDecoration;
import com.lion.graduation2.ui.adapter.BaseRecyclerViewAdapter;
import com.lion.graduation2.ui.adapter.XsbzRecyclerViewAdapter;
import com.lion.graduation2.ui.fragment.base.BaseTourFragment;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.HttpUtils;
import com.lion.graduation2.util.TimeUtils;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lion on 2015/4/24.
 */
public class XsbzFragment extends BaseTourFragment {

    private int sblx_id, xsnr_id;
    private FinalDb db;
    /* 全部XsbzBean对象和符合要求的 */
    private List<XsbzBean> all, xsbzBeans;

    private RecyclerView mRecyclerView;
    private XsbzRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /* 提交按钮 */
    private Button finishBtn;
    /* 进度框 */
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FinalDb.create(getActivity(), Constant.DB);
        all = db.findAll(XsbzBean.class);

        xsnr_id = getArguments().getInt("xsnr_id");
        sblx_id = places.get(pPosition).getSblxs().get(dPosition).getId();

        xsbzBeans = getXsbz(sblx_id, xsnr_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xsbz, container, false);

        finishBtn = (Button) view.findViewById(R.id.finish);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.xsbz_list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new XsbzRecyclerViewAdapter(xsbzBeans);
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private List<XsbzBean> getXsbz(int sblx_id, int xsnr_id) {
        List<XsbzBean> list = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getSblx_id() == sblx_id && all.get(i).getXsnr_id() == xsnr_id) {
                list.add(all.get(i));
            }
        }
        return list;
    }

    private void postData() {
        String json = history2Json(initHistoryData());
        Log.d(Constant.TAG, "history:" + json);
        FinalHttp fh = new FinalHttp();
        AjaxParams params = new AjaxParams();
        params.put("history", json);
        fh.post(HttpUtils.HttpUrl.POST_HISTORY_URL, params, new AjaxCallBack<Object>() {
            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
                dialog = ProgressDialog.show(getActivity(), null, "正在提交巡检结果，请稍后...");
            }

            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                dialog.dismiss();
                String result = (String) o;
                Log.d(Constant.TAG,"巡检结果提交："+result);
                getActivity().getSupportFragmentManager().popBackStack();
                Toast.makeText(getActivity(), "提交巡检结果成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                super.onFailure(t, strMsg);
                Toast.makeText(getActivity(), "提交失败，请重试", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(getActivity(), json, Toast.LENGTH_SHORT).show();
    }

    private HistoryBean initHistoryData() {
        HistoryBean historyBean = new HistoryBean();
        historyBean.setTask_id(tasks.get(tPostion).getId());
        historyBean.setPlace_id(places.get(pPosition).getId());
        historyBean.setDevice_id(sblx_id);
        historyBean.setXsnr_id(xsnr_id);
        historyBean.setTime(TimeUtils.getCurrentDate());
        List<ProblemBean> problems = new ArrayList<>();
        for (int i = 0; i < xsbzBeans.size(); i++) {
            if (xsbzBeans.get(i).getNote() != null) {
                ProblemBean problem = new ProblemBean();
                problem.setXsbz_id(xsbzBeans.get(i).getId());
                problem.setNote(xsbzBeans.get(i).getNote());
                problems.add(problem);
            }
        }
        historyBean.setProblems(problems);
        return historyBean;
    }

    private String history2Json(HistoryBean historyBean) {
        return new Gson().toJson(historyBean);
    }
}
