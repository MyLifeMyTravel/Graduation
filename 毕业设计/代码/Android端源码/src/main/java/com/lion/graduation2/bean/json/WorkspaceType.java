package com.lion.graduation2.bean.json;

/**
 * 工作站类型
 * Created by Lion on 2015/4/18.
 */
public class WorkspaceType {

    //工作站类型ID，自增
    private int _id;
    //工作站类型
    private String type;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WorkspaceType{" +
                "_id=" + _id +
                ", type='" + type + '\'' +
                '}';
    }
}
