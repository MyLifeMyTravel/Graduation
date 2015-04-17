package com.lion.graduation2.bean.json;

import java.io.Serializable;

/**
 * 巡检任务数据
 * Created by Lion on 2015/4/16.
 */
public class Task implements Serializable{

    //任务ID
    private int _id;
    //任务时间
    private String date = null;
    //巡检站点ID
    private int site_id;
    //巡检站点名称
    private String site_name = null;
    //任务类型
    private String type = null;
    //任务类型描述
    private String describle = null;
    //签到
    private String sign = null;
    //巡检状态
    private String status = null;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "_id=" + _id +
                ", date='" + date + '\'' +
                ", site_id=" + site_id +
                ", site_name='" + site_name + '\'' +
                ", type='" + type + '\'' +
                ", describle='" + describle + '\'' +
                ", sign='" + sign + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
