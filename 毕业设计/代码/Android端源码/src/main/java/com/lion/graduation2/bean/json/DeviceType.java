package com.lion.graduation2.bean.json;

/**
 * 设备类型
 * Created by Lion on 2015/4/18.
 */
public class DeviceType {

    //设备类型主键ID，自增
    private int _id;
    //所属工作站的ID
    private int space_id;
    //设备名称
    private String name;
    //设备参数
    private String parameter;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getSpace_id() {
        return space_id;
    }

    public void setSpace_id(int space_id) {
        this.space_id = space_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "_id=" + _id +
                ", space_id=" + space_id +
                ", name='" + name + '\'' +
                ", parameter='" + parameter + '\'' +
                '}';
    }
}
