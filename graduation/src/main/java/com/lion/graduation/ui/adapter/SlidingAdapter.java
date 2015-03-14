package com.lion.graduation.ui.adapter;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 侧滑菜单Fragement适配器
 * Created by Lion on 2015/3/13.
 */
public class SlidingAdapter extends BaseAdapter{

    private List<Fragment> fragments = null;

    /**
     * SlidingAdapter构造方法
     * @param fragments 需要显示的Fragement
     */
    public SlidingAdapter(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        int count = 0;
        if(null != fragments)
            count = fragments.size();
        return count;
    }

    @Override
    public Object getItem(int position) {
        Fragment item = null;
        if(null != fragments)
            item = fragments.get(position);
        return item;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
