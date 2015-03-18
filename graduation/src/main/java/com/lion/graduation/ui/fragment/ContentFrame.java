package com.lion.graduation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.LocationManagerProxy;
import com.lion.graduation.R;
import com.lion.graduation.model.TaskItem;
import com.lion.graduation.ui.adapter.TaskItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 开启应用显示的界面
 * Created by Lion on 2015/3/13.
 */
public class ContentFrame extends Fragment {

    //
    private LinearLayout weatherLayout = null;
    private TextView weather_content = null;
    //巡检信息显示列表
    private ListView mListView = null;
    private List<TaskItem> taskItems = null;

    //定位服务对象
    private LocationManagerProxy mLocationManagerProxy = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_layout, container, false);

        weatherLayout = (LinearLayout) view.findViewById(R.id.weather);
        weather_content = (TextView) weatherLayout.findViewById(R.id.weather_content);

        mListView = (ListView) view.findViewById(R.id.task_list);

        initTaskData();
        mListView.setAdapter(new TaskItemAdapter(taskItems));
        //设置ListView的Item点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }

    private void initTaskData() {
        taskItems = new ArrayList<>();
        TaskItem item = null;
        String[] task_name = getResources().getStringArray(R.array.task_name);
        String[] task_sign = getResources().getStringArray(R.array.task_sign);
        String[] task_status = getResources().getStringArray(R.array.task_status);

        for (int i = 0; i < task_name.length; i++) {
            item = new TaskItem();
            item.setTask_name(task_name[i]);
            item.setTask_sign(task_sign[i]);
            item.setTask_status(task_status[i]);
            taskItems.add(item);
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
                weather_content.setText("城市:" + city + "\n天气情况:" + weather + "\n风向:" + windDir + "\n风力:" + windPower + "\n空气湿度:" + humidity + "\n发布时间:" + reportTime);
                Toast.makeText(getActivity(), "城市:" + city + "\n天气情况:" + weather + "\n风向:" + windDir + "\n风力:" + windPower + "\n空气湿度:" + humidity + "\n发布时间:" + reportTime, Toast.LENGTH_LONG).show();
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
