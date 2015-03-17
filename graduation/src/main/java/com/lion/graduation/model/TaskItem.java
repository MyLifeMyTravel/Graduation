package com.lion.graduation.model;

/**
 * 任务列表Item模型
 * Created by Lion on 2015/3/17.
 */
public class TaskItem {

    private String task_name = null;
    private String task_sign = null;
    private String task_status = null;

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_sign() {
        return task_sign;
    }

    public void setTask_sign(String task_sign) {
        this.task_sign = task_sign;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "task_name='" + task_name + '\'' +
                ", task_sign='" + task_sign + '\'' +
                ", task_status='" + task_status + '\'' +
                '}';
    }
}
