package com.lion.graduation2.util;

import android.text.format.Time;

/**
 * Created by Lion on 2015/4/17.
 */
public class TimeUtils {

    public static int getMinutes(String time) {
        int minutes = 0;
        String[] t = time.split(":");
        minutes = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        return minutes;
    }

    public static String getTime() {
        String timeStr = null;
        Time time = new Time();
        time.setToNow();
        timeStr = time.hour + ":" + time.minute;
        return timeStr;
    }

    /**
     * 获取当前时间信息，格式yyyy-MM-dd hh:mm:ss
     *
     * @return
     */
    public static String getCurrentDate() {
        Time time = new Time();
        time.setToNow();
        return time.format("%Y-%m-%d %H:%M:%S");
    }
}
