package com.lion.graduation2.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.LocationManagerProxy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.TaskBean;
import com.lion.graduation2.bean.json.UserBean;
import com.lion.graduation2.bean.model.WeatherModel;
import com.lion.graduation2.ui.adapter.BaseRecyclerViewAdapter;
import com.lion.graduation2.ui.adapter.TaskListRecyclerViewAdapter;
import com.lion.graduation2.ui.fragment.base.BaseTourFragment;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.HttpUtils;
import com.lion.graduation2.util.TimeUtils;

import net.tsz.afinal.FinalDb;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 开启应用显示的界面
 * Created by Lion on 2015/3/13.
 */
public class ContentFragment extends BaseTourFragment {

    private WeatherModel weatherModel = null;

    //任务提示
    private TextView task_tip;

    //RecylerView对象
    private RecyclerView mRecyclerView;
    private TaskListRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //Weather模块的CardView
    private CardView weather_Card = null;
    private ImageView weather_icon = null;
    private TextView time, city, wind_dir, wind_power, humidity;

    //定位服务对象
    private LocationManagerProxy mLocationManagerProxy = null;

    //URL
    private String url = HttpUtils.HttpUrl.GET_TASK_URL;
    //FinalDb
    private FinalDb db;
    //UserBean
    private UserBean user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherModel = new WeatherModel();
        //获取存储的用户名、帐号
        db = FinalDb.create(getActivity(), Constant.DB);
        user = db.findAll(UserBean.class).get(0);

        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_layout, container, false);
        initWeatherCard(view);

        //初始化task_tip
        task_tip = (TextView) view.findViewById(R.id.task_tip);
        task_tip.setMovementMethod(new ScrollingMovementMethod());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.drawer_content);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TaskListRecyclerViewAdapter(tasks);
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                Fragment fragment = new TaskFragment();
                tPostion = position;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            }
        });

        initTaskData();
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void initWeatherCard(View view) {
        weather_Card = (CardView) view.findViewById(R.id.weather);
        weather_icon = (ImageView) weather_Card.findViewById(R.id.weather_icon);

        time = (TextView) weather_Card.findViewById(R.id.time);
        city = (TextView) weather_Card.findViewById(R.id.city);
        wind_dir = (TextView) weather_Card.findViewById(R.id.wind_dir);
        wind_power = (TextView) weather_Card.findViewById(R.id.wind_power);
        humidity = (TextView) weather_Card.findViewById(R.id.humidity);
    }

    private void initTaskData() {
        tasks.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = HttpUtils.httpGet(String.format(url, user.getAccount()));
                Log.d(Constant.TAG, "巡检任务数据:" + result);
                Gson gson = new Gson();
                try {
                    Type listType = new TypeToken<LinkedList<TaskBean>>() {
                    }.getType();
                    LinkedList<TaskBean> linkedTasks = gson.fromJson(result, listType);
                    if (linkedTasks != null) {
                        for (Iterator iterator = linkedTasks.iterator(); iterator.hasNext(); ) {
                            TaskBean task = (TaskBean) iterator.next();
                            Log.e(Constant.TAG, task.toString());
                            tasks.add(task);
                        }
                    }
                    //只有主线程才能刷新UI
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String tip = getResources().getString(R.string.task_tip);
                            task_tip.setText(String.format(tip, user.getName(), tasks.size()));
                            mAdapter.notifyDataSetChanged();
                            setAlarmClock();
                        }
                    });
                    Log.e(Constant.TAG, tasks.toString());
                } catch (JsonSyntaxException jsonSyntax) {
                    jsonSyntax.printStackTrace();
                    //只有主线程才能刷新UI
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "JSON数据解析失败，请重试", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWeatherProxy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //当onResume时使Task_tip重新获取焦点，可以滚动显示
        task_tip.requestFocus();
        task_tip.setFocusable(true);
    }

    private void initWeatherProxy() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(getActivity());
        mLocationManagerProxy.requestWeatherUpdates(LocationManagerProxy.WEATHER_TYPE_LIVE, weatherListener);
    }

    private AMapLocalWeatherListener weatherListener = new AMapLocalWeatherListener() {

        @Override
        public void onWeatherLiveSearched(AMapLocalWeatherLive aMapLocalWeatherLive) {
            if (aMapLocalWeatherLive != null && aMapLocalWeatherLive.getAMapException().getErrorCode() == 0) {
                city.setText(aMapLocalWeatherLive.getCity());
                weather_icon.setImageResource(setWeatherIcon(aMapLocalWeatherLive.getWeather()));
                time.setText(TimeUtils.getTime());
                wind_dir.setText("风向:" + aMapLocalWeatherLive.getWindDir());
                wind_power.setText("风力:" + aMapLocalWeatherLive.getWindPower());
                humidity.setText("湿度:" + aMapLocalWeatherLive.getHumidity());
            } else {
                // 获取实时天气失败
                Toast.makeText(getActivity(), "获取天气预报失败:" + aMapLocalWeatherLive.getAMapException().getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onWeatherForecaseSearched(AMapLocalWeatherForecast aMapLocalWeatherForecast) {

        }

        private int setWeatherIcon(String weather) {
            int status = 0;
            if (weather.equals("晴")) {
                status = R.drawable.cloud_clear;
            } else if (weather.contains("多云")) {
                status = R.drawable.cloud_cloudy;
            } else if (weather.contains("雷")) {
                status = R.drawable.cloud_cloudy;
            } else if (weather.contains("雨")) {
                status = R.drawable.cloud_rain;
            } else if (weather.equals("晴转多云")) {
                status = R.drawable.cloud_a_little_sunshine;
            } else if (weather.contains("雪")) {
                status = R.drawable.cloud_snow;
            }
            return status;
        }
    };

    private void setAlarmClock() {
        for (int i = 0; i < tasks.size(); i++) {
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            String time = tasks.get(i).getTime();
            Log.d(Constant.TAG,"巡检时间："+ time);
            String[] t = time.split(":");
            int hour = Integer.parseInt(t[0]);
            int minute = Integer.parseInt(t[1]);
            Log.d(Constant.TAG,"时间:"+hour+" 分钟:"+minute);
            intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
            intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, tasks.get(i).getDescription());
            intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
            startActivity(intent);
        }
    }
}
