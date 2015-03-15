package com.lion.graduation.model;

/**
 * 抽屉菜单ListView条目模型
 * Created by Lion on 2015/3/13.
 */
public class DrawerItem {

    private int icon;
    private String item = null;

    public DrawerItem(int icon, String item) {
        this.icon = icon;
        this.item = item;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
