package com.lion.graduation2.ui.fragement;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.Place;
import com.lion.graduation2.bean.json.Task;
import com.lion.graduation2.ui.DividerItemDecoration;
import com.lion.graduation2.ui.adapter.PlaceRecyclerViewAdapter;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.HttpUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 任务具体信息显示
 * Created by Lion on 2015/3/20.
 */
public class TaskFragment extends BaseFragment implements AMapLocationListener {

    private final static float DISTANCE = 300;
    private List<Place> places = new ArrayList<>();

    //签到、提交按钮
    private String[] sign = null;
    private CardView taskSign, taskCommit = null;
    private Button taskSignBtn, taskCommitBtn = null;

    //巡检地点ListView
    private Task task;

    //RecyclerView适配器
    private PlaceRecyclerViewAdapter mRecycleAdapter = null;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    //定位管理类
    private LocationManagerProxy mLocationManagerProxy = null;

    private String URL = HttpUtils.HttpUrl.GET_PLACE_URL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sign = getResources().getStringArray(R.array.sign);
        task = (Task) getArguments().getSerializable(Constant.Key.TASK_INFO);
        //设置ActionBar的Title
        getTitleListener().setTitle(task.getSite_name() + task.getType());

        mLocationManagerProxy = LocationManagerProxy.getInstance(getActivity());

        getPlaceList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_detail_layout, container, false);

        init(view);

        set();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置ActionBar的Icon是否可见
        getDispalyHomeListener().showDisplayHome(false);
    }

    private void init(View view) {
        //init
        taskSign = (CardView) view.findViewById(R.id.task_sign);
        taskSign.setCardBackgroundColor(getResources().getColor(R.color.lightskyblue));
        taskSignBtn = (Button) taskSign.findViewById(R.id.btn);

        taskCommit = (CardView) view.findViewById(R.id.task_commit);
        taskCommit.setCardBackgroundColor(getResources().getColor(R.color.lightskyblue));
        taskCommitBtn = (Button) taskCommit.findViewById(R.id.btn);

        mRecycleAdapter = new PlaceRecyclerViewAdapter(places);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.place);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        // specify an adapter (see also next example)
        mRecyclerView.setAdapter(mRecycleAdapter);
    }

    private void set() {
        //set
        taskSignBtn.setText(sign[0]);
        taskSignBtn.setBackgroundColor(Color.TRANSPARENT);

        taskCommitBtn.setText(getResources().getString(R.string.task_commit));
        taskCommitBtn.setBackgroundColor(Color.TRANSPARENT);

        //set click listener
        taskSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
                //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
                //在定位结束后，在合适的生命周期调用destroy()方法
                //其中如果间隔时间为-1，则定位只定一次
                mLocationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, -1, 15, TaskFragment.this);

                mLocationManagerProxy.setGpsEnable(false);
                taskSignBtn.setText(sign[1]);
            }
        });
        taskCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "正在提交", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        /**
         * 判断aMapLocation对象是否存在
         */
        if (aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0) {
            LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            float distance = AMapUtils.calculateLineDistance(latLng, new LatLng(29.907077, 121.644459));
            if (distance < DISTANCE) {
                Toast.makeText(getActivity(), distance + "", Toast.LENGTH_LONG).show();
                //设置CardView背景色
                //taskSign.setCardBackgroundColor(getResources().getColor(R.color.slategray));
                //taskSignBtn.setText(sign[2]);
                taskSign.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "签到成功，现在可以开始巡检啦！", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.tips_distance_too_long), Toast.LENGTH_LONG).show();
                taskSignBtn.setText(sign[0]);
            }
        } else {
            Toast.makeText(getActivity(), aMapLocation.getAMapException().getErrorMessage(), Toast.LENGTH_LONG).show();
            taskSignBtn.setText(sign[0]);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        getDispalyHomeListener().showDisplayHome(true);
    }

    private void getPlaceList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = HttpUtils.httpGet(String.format(URL, task.getSite_id()));
                Gson gson = new Gson();
                try {
                    Type listType = new TypeToken<LinkedList<Place>>() {
                    }.getType();
                    LinkedList<Place> items = gson.fromJson(result, listType);
                    for (Iterator iterator = items.iterator(); iterator.hasNext(); ) {
                        Place place = (Place) iterator.next();
                        Log.e(Constant.TAG, task.toString());
                        places.add(place);
                    }
                    //只有主线程才能刷新UI
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRecycleAdapter.notifyDataSetChanged();
                        }
                    });
                    Log.e(Constant.TAG, items.toString());
                } catch (JsonSyntaxException jsonSyntax) {
                    jsonSyntax.printStackTrace();
                }
            }
        }).start();
    }
}
