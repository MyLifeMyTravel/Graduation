package com.lion.graduation2.util.amap;

import android.content.Context;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;

/**
 * 坐标地址查询
 * Created by Lion on 2015/4/18.
 */
public class MyGeocodeSearch {

    private static MyGeocodeSearch instance;
    private static GeocodeSearch geocodeSearch;
    private OnRegeocodeListener onRegeocodeListener;
    private OnGeocodeListener onGeocodeListener;

    private MyGeocodeSearch(Context context) {
        geocodeSearch = new GeocodeSearch(context);
        geocodeSearch.setOnGeocodeSearchListener(onGeocodeSearchListener);
    }

    public static MyGeocodeSearch getInstance(Context context) {
        if (instance == null) {
            instance = new MyGeocodeSearch(context);
        }
        return instance;
    }

    public static void onQuery(String name, String city) {
        GeocodeQuery query = new GeocodeQuery(name, city);
        geocodeSearch.getFromLocationNameAsyn(query);
    }

    private GeocodeSearch.OnGeocodeSearchListener onGeocodeSearchListener = new GeocodeSearch.OnGeocodeSearchListener() {

        @Override
        public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

        }

        @Override
        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            if (i == 0) {
                if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null
                        && geocodeResult.getGeocodeAddressList().size() > 0) {
                    GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                    LatLonPoint point = geocodeAddress.getLatLonPoint();
                    onGeocodeListener.onResult(new LatLng(point.getLatitude(),point.getLongitude()));
                }
            }
        }
    };

    public interface OnRegeocodeListener {
        public void onResult(String address);
    }

    public interface OnGeocodeListener {
        public void onResult(LatLng point);
    }

    public OnGeocodeListener getOnGeocodeListener() {
        return onGeocodeListener;
    }

    public void setOnGeocodeListener(OnGeocodeListener onGeocodeListener) {
        this.onGeocodeListener = onGeocodeListener;
    }

    public OnRegeocodeListener getOnRegeocodeListener() {
        return onRegeocodeListener;
    }

    public void setOnRegeocodeListener(OnRegeocodeListener onRegeocodeListener) {
        this.onRegeocodeListener = onRegeocodeListener;
    }
}
