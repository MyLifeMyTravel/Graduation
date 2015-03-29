package com.lion.graduation.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lion.graduation.R;
import com.lion.graduation.model.DrawerItemModel;
import com.lion.graduation.util.BitmapUtils;

import java.util.List;

/**
 * RecycleView的Adapter
 * Created by Lion on 2015/3/18.
 */
public class DrawerRecycleAdapter extends BaseRecyclerViewAdapter {

    //任务Item常量标识
    private static final int TASK_ITEM = 1;
    private List<DrawerItemModel> mDataset = null;
    private Context context = null;

    public DrawerRecycleAdapter(List<DrawerItemModel> drawerItemModels) {
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

    public class DrawerTaskItemHolder extends DrawerItemHolder {

        public TextView textView;

        public DrawerTaskItemHolder(final View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.task_count);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        if (i == TASK_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.drawer_item_task_layout, viewGroup, false);
            return new DrawerTaskItemHolder(v);
        } else {
            View v = LayoutInflater.from(context).inflate(R.layout.draw_item_layout, viewGroup, false);
            return new DrawerItemHolder(v);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (mDataset.get(i).isTaskItem()) {
            DrawerTaskItemHolder holder = (DrawerTaskItemHolder) viewHolder;
            holder.textView.setText(mDataset.get(i).getCount()+"");
        }
        DrawerItemHolder holder = (DrawerItemHolder) viewHolder;
        holder.icon.setImageBitmap(BitmapUtils.readBitMap(context, mDataset.get(i).getIcon()));
        holder.text.setText(mDataset.get(i).getText());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        /**
         * 根据isTaskItem标志，返回不同的ItemView
         * <p>默认返回的是0，所以TASK_ITEM不能设为0</p>
         */
        if (mDataset.get(position).isTaskItem()) {
            return TASK_ITEM;
        } else {
            return super.getItemViewType(position);
        }
    }
}
