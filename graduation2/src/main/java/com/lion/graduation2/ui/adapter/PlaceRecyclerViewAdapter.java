package com.lion.graduation2.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.Place;

import java.util.List;

/**
 * Created by Lion on 2015/4/16.
 */
public class PlaceRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private List<Place> places = null;

    public PlaceRecyclerViewAdapter(List<Place> places) {
        this.places = places;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {

        public TextView place_name;
        public Button scan;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            this.place_name = (TextView) itemView.findViewById(R.id.place_name);
            this.scan = (Button) itemView.findViewById(R.id.scan);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item_layout, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PlaceViewHolder holder = (PlaceViewHolder) viewHolder;
        holder.place_name.setText(places.get(position).getName());
        holder.scan.setText("扫一扫");
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
