package com.lion.graduation.model;

/**
 * 抽屉菜单ListView条目模型
 * Created by Lion on 2015/3/13.
 */
public class DrawerItemModel {

    //需要显示Item的图标
    private int icon;
    //需要显示Item的文字说明
    private String text = null;

    public DrawerItemModel(int icon, String text) {
        this.icon = icon;
        this.text = text;
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
