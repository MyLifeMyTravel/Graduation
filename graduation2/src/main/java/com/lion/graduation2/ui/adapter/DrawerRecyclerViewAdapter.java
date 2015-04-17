package com.lion.graduation2.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.model.DrawerItemModel;
import com.lion.graduation2.util.BitmapUtils;

import java.util.List;

/**
 * 抽屉菜单的RecyclerAdapter
 * Created by Lion on 2015/3/18.
 */
public class DrawerRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    //任务Item常量标识
    private static final int TASK_ITEM = 1;
    private List<DrawerItemModel> mDataset = null;
    private Context context = null;

    public DrawerRecyclerViewAdapter(List<DrawerItemModel> drawerItemModels) {
        this.mDataset = drawerItemModels;
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
        context = viewGroup.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.navdrawer_item_layout, viewGroup, false);
        return new DrawerItemHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        DrawerItemHolder holder = (DrawerItemHolder) viewHolder;
        holder.icon.setImageBitmap(BitmapUtils.readBitMap(context, mDataset.get(i).getIcon()));
        holder.text.setText(mDataset.get(i).getText());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
