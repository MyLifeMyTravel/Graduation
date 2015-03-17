package com.lion.graduation.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lion.graduation.R;
import com.lion.graduation.model.TaskItem;

import java.util.List;

/**
 * 任务列表Item适配器
 * Created by Lion on 2015/3/17.
 */
public class TaskItemAdapter extends BaseAdapter {

    private List<TaskItem> taskItems = null;
    private Context mContext = null;
    private LayoutInflater mInflater = null;
    private ViewHolder holder = null;

    public TaskItemAdapter(List<TaskItem> taskItems, Context mContext) {
        this.taskItems = taskItems;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    static class ViewHolder {
        TextView mTask_Name;
        TextView mTask_Sign;
        TextView mTask_Status;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != taskItems)
            count = taskItems.size();
        return count;
    }

    @Override
    public Object getItem(int position) {
        TaskItem task = null;
        if (null != taskItems)
            task = taskItems.get(position);
        return task;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == holder)
            holder = new ViewHolder();

        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.task_item, parent, false);

            holder.mTask_Name = (TextView) convertView.findViewById(R.id.task_name);
            holder.mTask_Sign = (TextView) convertView.findViewById(R.id.task_sign);
            holder.mTask_Status = (TextView) convertView.findViewById(R.id.task_status);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTask_Name.setText(taskItems.get(position).getTask_name());
        holder.mTask_Sign.setText(taskItems.get(position).getTask_sign());
        holder.mTask_Status.setText(taskItems.get(position).getTask_status());
        return convertView;
    }
}
