package com.lion.graduation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lion.graduation.R;
import com.lion.graduation.model.DrawerItemModel;

import java.util.List;

/**
 * RecycleView的Adapter
 * Created by Lion on 2015/3/18.
 */
public class RecycleAdapter extends BaseRecyclerViewAdapter {

    private List<DrawerItemModel> drawerItemModels = null;

    public RecycleAdapter(List<DrawerItemModel> drawerItemModels) {
        this.drawerItemModels = drawerItemModels;
    }

    public class DrawerItemHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView text;

        public DrawerItemHolder(final View itemView) {
            super(itemView);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.text = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != getOnItemClickListener()) {
                        getOnItemClickListener().onItemClick(itemView, getPosition());
                    }
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.draw_item_layout, viewGroup, false);
        RecyclerView.ViewHolder holder = new DrawerItemHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        DrawerItemHolder holder = (DrawerItemHolder) viewHolder;
        holder.icon.setImageResource(drawerItemModels.get(i).getIcon());
        holder.text.setText(drawerItemModels.get(i).getText());
    }

    @Override
    public int getItemCount() {
        return drawerItemModels.size();
    }

}
