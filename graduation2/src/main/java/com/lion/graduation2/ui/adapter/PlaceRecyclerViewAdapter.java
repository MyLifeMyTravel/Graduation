package com.lion.graduation2.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.CdlxBean;
import com.lion.graduation2.bean.json.PlaceBean;
import com.lion.graduation2.bean.json.SblxBean;
import com.lion.graduation2.util.Constant;

import net.tsz.afinal.FinalDb;

import java.util.List;

/**
 * Created by Lion on 2015/4/16.
 */
public class PlaceRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private List<PlaceBean> places = null;
    private FinalDb db;
    private List<SblxBean> sblxs = null;
//    private String[] items = {"开关柜、电流互感器、断路器（开关）、电缆出线、保护及测控部分", "开关柜、电流互感器、断路器（开关）、电缆出线、保护及测控部分", "开关柜、电流互感器、断路器（开关）、电缆出线、保护及测控部分", "主变本体、主变套管、主变风冷系统、主变中性点设备、主变端子箱", "高压室、电压互感器、线路开关、主变低压侧开关柜、站用变", "主变本体、主变套管、主变风冷系统、主变中性点设备、主变端子箱", "主控室、远动屏、直流系统、1号主变保护屏、2号主变保护屏"};

    public PlaceRecyclerViewAdapter(List<PlaceBean> places) {
        this.places = places;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {

        public TextView place_name, devices;

        public PlaceViewHolder(final View itemView) {
            super(itemView);
            this.place_name = (TextView) itemView.findViewById(R.id.place_name);
            this.devices = (TextView) itemView.findViewById(R.id.devices);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item_layout, parent, false);
        db = FinalDb.create(parent.getContext(), Constant.DB);
        sblxs = db.findAll(SblxBean.class);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PlaceViewHolder holder = (PlaceViewHolder) viewHolder;
        holder.place_name.setText(places.get(position).getName());
        String items = "";
        int cdlx_id = places.get(position).getCdlx_id();
        for (int i = 0; i < sblxs.size(); i++) {
            if (sblxs.get(i).getCdlx_id() == cdlx_id) {
                items += sblxs.get(i).getName();
            }
        }
        holder.devices.setText("巡检项目:" + items);
        //holder.devices.setText("巡检项目：" + items[position]);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (places != null) {
            count = places.size();
        }
        return count;
    }
}
