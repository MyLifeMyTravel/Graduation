package com.lion.graduation2.bean.json;

import java.io.Serializable;

/**
 * 变电站数据
 * Created by Lion on 2015/4/16.
 */
public class SiteBean implements Serializable {

    //变电站ID
    private int id;
    //变电站编号
    private String indentifier;
    //变电站位置
    private String loc;
    //变电站名称
    private String name;
    //变电站坐标，可为空
    private String point;
    //变电站所在省
    private String province;
    //变电站所在城市
    private String city;
    //变电站所在地区
    private String area;
    //变电站所在街道
    private String street;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndentifier() {
        return indentifier;
    }

    public void setIndentifier(String indentifier) {
        this.indentifier = indentifier;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "SiteBean{" +
                "id=" + id +
                ", indentifier='" + indentifier + '\'' +
                ", loc='" + loc + '\'' +
                ", name='" + name + '\'' +
                ", point='" + point + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
