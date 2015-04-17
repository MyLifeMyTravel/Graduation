package com.lion.graduation2.bean.model;

/**
 * 抽屉菜单ListView条目模型
 * <p>该模型还需要优化，isTaskItem和count不是每个条目都需要的</p>
 * Created by Lion on 2015/3/13.
 */
public class DrawerItemModel {

    //需要显示Item的图标
    private int icon;
    //需要显示Item的文字说明
    private String text = null;
    private int count = 0;

    public DrawerItemModel(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
