package com.lion.graduation2.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lion.graduation2.bean.json.DeviceHistoryBean;
import com.lion.graduation2.bean.json.XsnrBean;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.HttpUtils;
import com.lion.graduation2.util.chart.BudgetPieChart;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 用于显示饼图
 * Created by Lion on 2015/4/25.
 */
public class PieChartActivity extends ActionBarActivity {

    private List<DeviceHistoryBean> deviceHistoryBeans;
    private int device_id;
    private String[] items;
    private double[] values;
    private String device_name;
    private ProgressDialog dialog;
    private FinalDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        device_id = getIntent().getIntExtra("device_id", 0);
        device_name = getIntent().getStringExtra("device_name");
        db = FinalDb.create(PieChartActivity.this, Constant.DB);
        getDeviceHistory();
    }

    private void getDeviceHistory() {
        FinalHttp fh = new FinalHttp();
        AjaxParams params = new AjaxParams();
        params.put("device_id", device_id + "");
        fh.post(HttpUtils.HttpUrl.GET_DEVICE_HISTORY_URL, params, new AjaxCallBack<Object>() {
            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
                dialog = ProgressDialog.show(PieChartActivity.this, null, "正在获取巡检历史...");
            }

            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                dialog.dismiss();
                String result = (String) o;
                Log.d(Constant.TAG, device_name + "的巡检记录:" + result);
                Gson gson = new Gson();
                try {
                    deviceHistoryBeans = new ArrayList<DeviceHistoryBean>();
                    Type listType = new TypeToken<LinkedList<DeviceHistoryBean>>() {
                    }.getType();
                    LinkedList<DeviceHistoryBean> items = gson.fromJson(result, listType);
                    for (Iterator iterator = items.iterator(); iterator.hasNext(); ) {
                        DeviceHistoryBean deviceHistory = (DeviceHistoryBean) iterator.next();
                        Log.e(Constant.TAG, deviceHistory.toString());
                        deviceHistory.setXsnr_nr(db.findById(deviceHistory.getXsnr_id(), XsnrBean.class).getNr());
                        deviceHistoryBeans.add(deviceHistory);
                    }
                    //更新UI
                    Log.e(Constant.TAG, items.toString());
                } catch (JsonSyntaxException jsonSyntax) {
                    jsonSyntax.printStackTrace();
                }
                setContentView(new BudgetPieChart().execute(PieChartActivity.this, device_name + "巡检记录分析", getItems(), getValues()));
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                super.onFailure(t, strMsg);
                dialog.dismiss();
                Toast.makeText(PieChartActivity.this, "下载数据失败:" + strMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String[] getItems() {
        String[] items = new String[deviceHistoryBeans.size()];
        for (int i = 0; i < deviceHistoryBeans.size(); i++) {
            items[i] = deviceHistoryBeans.get(i).getXsnr_nr();
        }
        Log.d(Constant.TAG, "Items:" + items.toString());
        return items;
    }

    private double[] getValues() {
        double[] values = new double[deviceHistoryBeans.size()];
        for (int i = 0; i < deviceHistoryBeans.size(); i++) {
            values[i] = deviceHistoryBeans.get(i).getCount();
        }
        return values;
    }
}
