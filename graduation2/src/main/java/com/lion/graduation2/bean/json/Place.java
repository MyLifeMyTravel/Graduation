package com.lion.graduation2.bean.json;

/**
 * 巡检地点数据
 * Created by Lion on 2015/4/16.
 */
public class Place {

    //巡检场地ID
    private int _id;
    //巡检站点ID
    private int site_id;
    //地点名称
    private String name = null;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Place{" +
                "_id=" + _id +
                ", site_id=" + site_id +
                ", name='" + name + '\'' +
                '}';
    }
}
