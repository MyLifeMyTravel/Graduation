package com.lion.graduation2.ui.adapter;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lion.graduation2.R;
import com.lion.graduation2.activity.PieChartActivity;
import com.lion.graduation2.activity.UserActivity;
import com.lion.graduation2.bean.json.SblxBean;

import java.util.List;

/**
 * Created by Lion on 2015/4/24.
 */
public class DeviceRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private List<SblxBean> sblxBeans = null;

    public DeviceRecyclerViewAdapter(List<SblxBean> sblxBeans) {
        this.sblxBeans = sblxBeans;
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {

        private TextView device, status;
        private Button historyBtn;

        public DeviceViewHolder(final View itemView) {
            super(itemView);
            this.device = (TextView) itemView.findViewById(R.id.device);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.historyBtn = (Button) itemView.findViewById(R.id.history);
            this.historyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), PieChartActivity.class);
                    intent.putExtra("device_id", sblxBeans.get(getPosition()).getId());
                    intent.putExtra("device_name", sblxBeans.get(getPosition()).getName());
                    itemView.getContext().startActivity(intent);
                }
            });
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getOnItemClickListener() != null) {
                        getOnItemClickListener().onItemClick(itemView, getPosition());
                    }
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_item_layout, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        DeviceViewHolder holder = (DeviceViewHolder) viewHolder;
        holder.device.setText(sblxBeans.get(position).getName());
        if (sblxBeans.get(position).isStatus()) {
            holder.status.setText("已巡检");
        } else {
            holder.status.setText("未巡检");
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (sblxBeans != null) {
            count = sblxBeans.size();
        }
        return count;
    }
}
