package com.lion.graduation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lion.graduation.R;
import com.lion.graduation.model.TaskItemModel;

import java.util.List;

/**
 * 主界面显示的RecyclerViewAdapter
 * Created by Lion on 2015/3/19.
 */
public class TaskRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private static final int TYPE_WEATHER = 0;
    private List<TaskItemModel> items = null;

    public TaskRecyclerViewAdapter(List<TaskItemModel> items) {
        this.items = items;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView task_name, task_sign, task_status;

        public TaskViewHolder(final View itemView) {
            super(itemView);
            this.task_name = (TextView) itemView.findViewById(R.id.task_name);
            this.task_sign = (TextView) itemView.findViewById(R.id.task_sign);
            this.task_status = (TextView) itemView.findViewById(R.id.task_status);
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
        holder.task_name.setText(items.get(i).getTask_name());
        holder.task_sign.setText(items.get(i).getTask_sign());
        holder.task_status.setText(items.get(i).getTask_status());
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
