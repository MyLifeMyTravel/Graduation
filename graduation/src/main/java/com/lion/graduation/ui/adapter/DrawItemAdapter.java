package com.lion.graduation.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lion.graduation.R;
import com.lion.graduation.model.DrawerItem;

import java.util.List;

/**
 * 抽屉菜单ListView适配器
 * Created by Lion on 2015/3/13.
 */
public class DrawItemAdapter extends BaseAdapter {

    private List<DrawerItem> items = null;
    private ViewHolder holder = null;

    public DrawItemAdapter(List<DrawerItem> items) {
        this.items = items;
    }

    static class ViewHolder {
        ImageView icon;
        TextView text;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != items)
            count = items.size();
        return count;
    }

    @Override
    public Object getItem(int position) {
        DrawerItem item = null;
        if (null != items)
            item = items.get(position);
        return item;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.draw_item, parent, false);

            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.text = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.icon.setImageResource(items.get(position).getIcon());
        holder.text.setText(items.get(position).getText());
        return convertView;
    }
}
