package com.lion.graduation.ui.fragment;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.lion.graduation.R;
import com.lion.graduation.ui.DividerItemDecoration;
import com.lion.graduation.ui.adapter.BaseRecyclerViewAdapter;

/**
 * 任务具体信息显示
 * Created by Lion on 2015/3/20.
 */
public class TaskFragment extends Fragment implements AMapLocationListener {

    private final static float DISTANCE = 300;
    //RecylerView对象
    private RecyclerView mRecyclerView;
    private DevicesItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String[] sign = null;
    private CardView taskSign, taskCommit = null;
    private Button taskSignBtn, taskCommitBtn = null;

    private LocationManagerProxy mLocationManagerProxy = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sign = getResources().getStringArray(R.array.sign);

        mLocationManagerProxy = LocationManagerProxy.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task, container, false);

        init(view);

        set();

        return view;
    }

    private void init(View view) {
        //init
        taskSign = (CardView) view.findViewById(R.id.task_sign);
        taskSign.setCardBackgroundColor(getResources().getColor(R.color.lightskyblue));
        taskSignBtn = (Button) taskSign.findViewById(R.id.btn);

        taskCommit = (CardView) view.findViewById(R.id.task_commit);
        taskCommit.setCardBackgroundColor(getResources().getColor(R.color.lightskyblue));
        taskCommitBtn = (Button) taskCommit.findViewById(R.id.btn);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.task_devices);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new DevicesItemAdapter();
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
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

        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Fragment fragment = new DeviceOverhaulFragment();
                if (position == 0) {
                    //fragment = new ContentFragement();
                    //关闭左侧的抽屉菜单
                } else if (position == 1) {

                } else if (position == 2) {

                } else if (position == 3) {

                } else if (position == 4) {

                }

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        float distance = AMapUtils.calculateLineDistance(latLng, new LatLng(29.907077, 121.644459));
        if (distance < DISTANCE) {
            Toast.makeText(getActivity(), distance + "", Toast.LENGTH_LONG).show();
            //设置CardView背景色
            taskSign.setCardBackgroundColor(getResources().getColor(R.color.slategray));
            taskSignBtn.setText(sign[2]);
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.tips_distance_too_long), Toast.LENGTH_LONG).show();
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

    /**
     * 已知Bug，点击某一个条目，下拉刷新的时候另外也会出现一样的点击操作
     */
    class DevicesItemAdapter extends BaseRecyclerViewAdapter {

        private boolean[] isVisibility = new boolean[getItemCount()];

        public class DevicesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            Button click1, click2;
            ImageButton imageButton;
            LinearLayout hidden;

            public DevicesViewHolder(final View itemView) {
                super(itemView);
                click1 = (Button) itemView.findViewById(R.id.click1);
                click2 = (Button) itemView.findViewById(R.id.click2);
                imageButton = (ImageButton) itemView.findViewById(R.id.device_drop_button);

                hidden = (LinearLayout) itemView.findViewById(R.id.hidden);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("lion", "position:" + getPosition());
                        if (hidden.getVisibility() == View.VISIBLE) {
                            hidden.setVisibility(View.GONE);
                            isVisibility[getPosition()] = false;
                        } else {
                            hidden.setVisibility(View.VISIBLE);
                            isVisibility[getPosition()] = true;
                        }
                    }
                });
                //set click listener
                //click1.setOnClickListener(this);
                //click2.setOnClickListener(this);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.click1:
                        click2.setVisibility(View.VISIBLE);
                        break;
                    case R.id.click2:
                        click2.setVisibility(View.GONE);
                        break;
                    default:
                        if (null != getOnItemClickListener())
                            getOnItemClickListener().onItemClick(v, getPosition());
                }
            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_devices_item, parent, false);
            return new DevicesViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            DevicesViewHolder holder = (DevicesViewHolder) viewHolder;
            if (isVisibility[position]) {
                holder.hidden.setVisibility(View.VISIBLE);
            } else {
                holder.hidden.setVisibility(View.GONE);
            }
            holder.click1.setText("" + position);
            holder.click2.setText("" + position);
        }

        @Override
        public int getItemCount() {
            return 10000;
        }
    }
}
