package com.lion.graduation2.util;

import android.text.format.Time;

/**
 * Created by Lion on 2015/4/17.
 */
public class TimeUtils {

    public static String getTime() {
        String timeStr = null;
        Time time = new Time();
        time.setToNow();
        timeStr = time.hour + ":" + time.minute;
        return timeStr;
    }
}
