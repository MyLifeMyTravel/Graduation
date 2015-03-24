package com.lion.graduation.Application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

/**
 * Created by Lion on 2015/3/21.
 */
public class MyApplication extends Application implements AMapLocationListener {

    public LocationManagerProxy mLocationManagerProxy = null;
    public SharedPreferences sp = null;
    protected SharedPreferences.Editor editor = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //开多个位置监听，会导致监听时间只能是第一个的，导致回调变慢
        //init();
    }

    private void init() {
        //获取Sharepreference对象
        sp = getSharedPreferences("location", Context.MODE_PRIVATE);
        editor = sp.edit();

        mLocationManagerProxy = LocationManagerProxy.getInstance(this);

        //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
        //在定位结束后，在合适的生命周期调用destroy()方法
        //其中如果间隔时间为-1，则定位只定一次
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 60 * 1000, 15, this);

        mLocationManagerProxy.setGpsEnable(false);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0) {
            //获取位置信息
            Double geoLat = aMapLocation.getLatitude();
            Double geoLng = aMapLocation.getLongitude();

            //每次当位置改变时，将数据写入到Sharepreference
            editor.putString("time", geoLat + ":" + geoLng);
            editor.commit();
            Log.d("location", geoLat + ":" + geoLng);
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


}
