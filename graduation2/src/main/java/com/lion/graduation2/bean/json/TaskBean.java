package com.lion.graduation2.bean.json;

import java.io.Serializable;

/**
 * 巡检任务数据
 * Created by Lion on 2015/4/16.
 */
public class TaskBean implements Serializable {

    //任务ID
    private int id;
    //任务类型
    private String rwlx;
    //任务描述
    private String description;
    //任务日期
    private String date = null;
    //任务时间
    private String time = null;
    //完成状态
    private String flag = null;
    //变电站ID
    private SiteBean site;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRwlx() {
        return rwlx;
    }

    public void setRwlx(String rwlx) {
        this.rwlx = rwlx;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public SiteBean getSite() {
        return site;
    }

    public void setSite(SiteBean site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "id=" + id +
                ", rwlx='" + rwlx + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", flag='" + flag + '\'' +
                ", site=" + site +
                '}';
    }
}
