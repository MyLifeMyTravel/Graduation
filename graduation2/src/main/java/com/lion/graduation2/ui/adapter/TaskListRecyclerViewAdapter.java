package com.lion.graduation2.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.TaskBean;
import com.lion.graduation2.util.TimeUtils;

import java.util.List;

/**
 * 巡检任务列表显示的RecyclerViewAdapter
 * Created by Lion on 2015/3/19.
 */
public class TaskListRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private static final int TYPE_WEATHER = 0;
    private List<TaskBean> items = null;

    public TaskListRecyclerViewAdapter(List<TaskBean> items) {
        this.items = items;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        private ImageView nav;
        private TextView task_type, task_name, task_time;

        public TaskViewHolder(final View itemView) {
            super(itemView);
            this.task_type = (TextView) itemView.findViewById(R.id.task_type);
            this.task_name = (TextView) itemView.findViewById(R.id.task_name);
            this.task_time = (TextView) itemView.findViewById(R.id.task_time);
            this.nav = (ImageView) itemView.findViewById(R.id.navBtn);

            this.nav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item_layout, viewGroup, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        TaskViewHolder holder = (TaskViewHolder) viewHolder;
        holder.task_type.setText(items.get(i).getRwlx());
        holder.task_name.setText(items.get(i).getSite().getName());
        //当已经超过任务时间，则显示字体变红色
        if (TimeUtils.getMinutes(items.get(i).getTime()) < TimeUtils.getMinutes(TimeUtils.getTime())) {
            holder.task_time.setTextColor(Color.RED);
        }
        holder.task_time.setText(items.get(i).getDate() + " " + items.get(i).getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
