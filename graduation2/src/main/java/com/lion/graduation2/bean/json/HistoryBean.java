package com.lion.graduation2.bean.json;

import java.util.List;

/**
 * Created by Lion on 2015/4/24.
 */
public class HistoryBean {

    /* 任务编号 */
    private int task_id;
    /* 场地编号,即cdlx_id */
    private int place_id;
    /* 设备编号,即sblx_id */
    private int device_id;
    /* 巡视内容编号 */
    private int xsnr_id;
    /* 巡视问题 */
    private List<ProblemBean> problems;
    /* 数据上传时间 */
    private String time;

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getXsnr_id() {
        return xsnr_id;
    }

    public void setXsnr_id(int xsnr_id) {
        this.xsnr_id = xsnr_id;
    }

    public List<ProblemBean> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemBean> problems) {
        this.problems = problems;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HistoryBean{" +
                "task_id=" + task_id +
                ", place_id=" + place_id +
                ", device_id=" + device_id +
                ", xsnr_id=" + xsnr_id +
                ", problems=" + problems +
                ", time='" + time + '\'' +
                '}';
    }
}
