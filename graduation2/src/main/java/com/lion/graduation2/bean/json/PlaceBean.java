package com.lion.graduation2.bean.json;

import java.io.Serializable;

/**
 * 巡检地点数据
 * Created by Lion on 2015/4/16.
 */
public class PlaceBean implements Serializable {

    //Place的ID
    private int id;
    //Place的标识
    private String identifier;
    //场地类型ID
    private int cdlx_id;
    //巡检站点ID
    private int site_id;
    //地点名称
    private String name = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getCdlx_id() {
        return cdlx_id;
    }

    public void setCdlx_id(int cdlx_id) {
        this.cdlx_id = cdlx_id;
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
        return "PlaceBean{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", cdlx_id=" + cdlx_id +
                ", site_id=" + site_id +
                ", name='" + name + '\'' +
                '}';
    }
}
