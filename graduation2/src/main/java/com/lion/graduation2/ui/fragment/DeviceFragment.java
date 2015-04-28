package com.lion.graduation2.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.PlaceBean;
import com.lion.graduation2.bean.json.SblxBean;
import com.lion.graduation2.ui.DividerItemDecoration;
import com.lion.graduation2.ui.adapter.BaseRecyclerViewAdapter;
import com.lion.graduation2.ui.adapter.DeviceRecyclerViewAdapter;
import com.lion.graduation2.ui.fragment.base.BaseTourFragment;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.HttpUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.List;

/**
 * Created by Lion on 2015/4/24.
 */
public class DeviceFragment extends BaseTourFragment {

    private PlaceBean place;
    private Button finishBtn;

    //RecylerView对象
    private RecyclerView mRecyclerView;
    private DeviceRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        place = places.get(pPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);

        finishBtn = (Button) view.findViewById(R.id.finish);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFinish = true;
                List<SblxBean> sblxBeans = place.getSblxs();
                for (int i = 0; i < sblxBeans.size(); i++) {
                    if (!sblxBeans.get(i).isStatus()) {
                        isFinish = false;
                    }
                }
                if (isFinish) {
                    places.get(pPosition).setStatus(true);
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "您尚未完成当前位置的巡检", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.device_list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new DeviceRecyclerViewAdapter(place.getSblxs());
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Fragment fragment = new XsnrFragment();
                dPosition = position;
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_frame, fragment).commit();
            }
        });
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

}
