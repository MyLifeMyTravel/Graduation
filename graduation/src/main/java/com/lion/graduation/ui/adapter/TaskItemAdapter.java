package com.lion.graduation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lion.graduation.R;
import com.lion.graduation.model.TaskItemModel;

import java.util.List;

/**
 * 任务列表Item适配器
 * Created by Lion on 2015/3/17.
 */
public class TaskItemAdapter extends BaseAdapter {

    private List<TaskItemModel> taskItemModels = null;
    private ViewHolder holder = null;

    public TaskItemAdapter(List<TaskItemModel> taskItemModels) {
        this.taskItemModels = taskItemModels;
    }

    static class ViewHolder {
        TextView mTask_Name;
        TextView mTask_Sign;
        TextView mTask_Status;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != taskItemModels)
            count = taskItemModels.size();
        return count;
    }

    @Override
    public Object getItem(int position) {
        TaskItemModel task = null;
        if (null != taskItemModels)
            task = taskItemModels.get(position);
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_layout, parent, false);

            holder.mTask_Name = (TextView) convertView.findViewById(R.id.task_name);
            holder.mTask_Sign = (TextView) convertView.findViewById(R.id.task_sign);
            holder.mTask_Status = (TextView) convertView.findViewById(R.id.task_status);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTask_Name.setText(taskItemModels.get(position).getTask_name());
        holder.mTask_Sign.setText(taskItemModels.get(position).getTask_sign());
        holder.mTask_Status.setText(taskItemModels.get(position).getTask_status());
        return convertView;
    }
}
