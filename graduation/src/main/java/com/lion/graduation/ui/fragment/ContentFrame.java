package com.lion.graduation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lion.graduation.R;
import com.lion.graduation.model.TaskItem;
import com.lion.graduation.ui.adapter.TaskItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 开启应用显示的界面
 * Created by Lion on 2015/3/13.
 */
public class ContentFrame extends Fragment {

    //巡检信息显示列表
    private ListView mListView = null;
    private List<TaskItem> taskItems = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_layout, container, false);

        mListView = (ListView) view.findViewById(R.id.task_list);

        initTaskData();
        mListView.setAdapter(new TaskItemAdapter(taskItems, getActivity()));
        return view;
    }

    private void initTaskData() {
        taskItems = new ArrayList<>();
        TaskItem item = null;
        String[] task_name = getResources().getStringArray(R.array.task_name);
        String[] task_sign = getResources().getStringArray(R.array.task_sign);
        String[] task_status = getResources().getStringArray(R.array.task_status);

        for (int i = 0; i < task_name.length; i++) {
            item = new TaskItem();
            item.setTask_name(task_name[i]);
            item.setTask_sign(task_sign[i]);
            item.setTask_status(task_status[i]);
            taskItems.add(item);
        }
    }
}
