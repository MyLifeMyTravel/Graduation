package com.lion.graduation2;

import android.app.Activity;
import android.app.Application;

import com.amap.api.navi.AMapNavi;
import com.iflytek.cloud.SpeechUtility;
import com.lion.graduation2.util.TTSController;

import java.util.ArrayList;

/**
 * Created by Lion on 2015/4/26.
 */
public class MainApplication extends Application {
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private static MainApplication instance;

    private MainApplication() {
    }

    // 单例模式中获取唯一的MyApplication实例
    public static MainApplication getInstance() {
        if (null == instance) {
            instance = new MainApplication();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void deleteActivity(Activity activity) {
        activities.remove(activity);
    }

    // finish
    public void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
        activities.clear();

    }
}
