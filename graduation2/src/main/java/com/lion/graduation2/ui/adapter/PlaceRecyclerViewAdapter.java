package com.lion.graduation2.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.CdlxBean;
import com.lion.graduation2.bean.json.PlaceBean;
import com.lion.graduation2.bean.json.SblxBean;
import com.lion.graduation2.util.Constant;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lion on 2015/4/16.
 */
public class PlaceRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private Context context;
    private List<PlaceBean> places = null;
    private FinalDb db;
    private List<SblxBean> sblxs = null;

    public PlaceRecyclerViewAdapter(List<PlaceBean> places) {
        this.places = places;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {

        public TextView place, status;
        public Button btn;
        //public ListView device_list;

        public PlaceViewHolder(final View itemView) {
            super(itemView);
            this.place = (TextView) itemView.findViewById(R.id.place);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.btn = (Button) itemView.findViewById(R.id.btn);
            this.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item_layout, parent, false);
        context = parent.getContext();
        db = FinalDb.create(context, Constant.DB);
        sblxs = db.findAll(SblxBean.class);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PlaceViewHolder holder = (PlaceViewHolder) viewHolder;
        holder.place.setText(places.get(position).getName());
        if (places.get(position).isStatus()) {
            holder.status.setText("已完成");
        } else {
            holder.status.setText("未完成");
        }
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
