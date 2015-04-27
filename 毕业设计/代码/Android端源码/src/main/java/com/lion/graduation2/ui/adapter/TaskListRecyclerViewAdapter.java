package com.lion.graduation2.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.AlarmClock;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.TaskBean;
import com.lion.graduation2.util.Constant;
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

        private LinearLayout clock;
        private TextView task_type, task_name, task_time;

        public TaskViewHolder(final View itemView) {
            super(itemView);
            this.task_type = (TextView) itemView.findViewById(R.id.task_type);
            this.task_name = (TextView) itemView.findViewById(R.id.task_name);
            this.task_time = (TextView) itemView.findViewById(R.id.task_time);
            this.clock = (LinearLayout) itemView.findViewById(R.id.clock);

            this.clock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAlarmClock(v.getContext(), items.get(getPosition()));
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

    private void setAlarmClock(Context context, TaskBean task) {
        String time = task.getTime();
        //当时间未超过时，设置闹钟
        //if (TimeUtils.getMinutes(time) > TimeUtils.getMinutes(TimeUtils.getTime())) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        Log.d(Constant.TAG, "巡检时间：" + time);
        String[] t = time.split(":");
        int hour = Integer.parseInt(t[0]);
        int minute = Integer.parseInt(t[1]);
        Log.d(Constant.TAG, "时间:" + hour + " 分钟:" + minute);
        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, task.getDescription());
        //不显示系统闹钟UI
        //intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        context.startActivity(intent);
        //}
    }
}
