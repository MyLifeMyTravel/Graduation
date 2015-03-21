package com.lion.graduation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.LocationManagerProxy;
import com.lion.graduation.R;
import com.lion.graduation.model.TaskItemModel;
import com.lion.graduation.ui.adapter.BaseRecyclerViewAdapter;
import com.lion.graduation.ui.adapter.TaskRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 开启应用显示的界面
 * Created by Lion on 2015/3/13.
 */
public class ContentFragement extends Fragment {

    private List<TaskItemModel> items = null;

    //RecylerView对象
    private RecyclerView mRecyclerView;
    private TaskRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //Weather模块的CardView
    private CardView weather_Card = null;
    private TextView weather_Content = null;

    //定位服务对象
    private LocationManagerProxy mLocationManagerProxy = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTaskData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_layout, container, false);
        initWeatherCard(view);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.drawer_content);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TaskRecyclerViewAdapter(items);
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new TaskFragment()).addToBackStack(null).commit();
                Toast.makeText(getActivity(), position + "", Toast.LENGTH_LONG).show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void initWeatherCard(View view) {
        weather_Card = (CardView) view.findViewById(R.id.weather);
        weather_Content = (TextView) weather_Card.findViewById(R.id.weather_content);
    }

    private void initTaskData() {
        items = new ArrayList<>();
        TaskItemModel item = null;
        String[] task_name = getResources().getStringArray(R.array.task_name);
        String[] task_sign = getResources().getStringArray(R.array.task_sign);
        String[] task_status = getResources().getStringArray(R.array.task_status);

        for (int i = 0; i < task_name.length; i++) {
            item = new TaskItemModel();
            item.setTask_name(task_name[i]);
            item.setTask_sign(task_sign[i]);
            item.setTask_status(task_status[i]);
            items.add(item);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWeatherProxy();
    }

    private void initWeatherProxy() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(getActivity());
        mLocationManagerProxy.requestWeatherUpdates(LocationManagerProxy.WEATHER_TYPE_LIVE, weatherListener);
    }

    private AMapLocalWeatherListener weatherListener = new AMapLocalWeatherListener() {

        @Override
        public void onWeatherLiveSearched(AMapLocalWeatherLive aMapLocalWeatherLive) {
            if (aMapLocalWeatherLive != null && aMapLocalWeatherLive.getAMapException().getErrorCode() == 0) {
                String city = aMapLocalWeatherLive.getCity();//城市
                String weather = aMapLocalWeatherLive.getWeather();//天气情况
                String windDir = aMapLocalWeatherLive.getWindDir();//风向
                String windPower = aMapLocalWeatherLive.getWindPower();//风力
                String humidity = aMapLocalWeatherLive.getHumidity();//空气湿度
                String reportTime = aMapLocalWeatherLive.getReportTime();//数据发布时间
                weather_Content.setText("城市:" + city + "\n天气情况:" + weather + "\n风向:" + windDir + "\n风力:" + windPower + "\n空气湿度:" + humidity + "\n发布时间:" + reportTime);
            } else {
                // 获取实时天气失败
                Toast.makeText(getActivity(), "获取天气预报失败:" + aMapLocalWeatherLive.getAMapException().getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onWeatherForecaseSearched(AMapLocalWeatherForecast aMapLocalWeatherForecast) {

        }
    };
}
