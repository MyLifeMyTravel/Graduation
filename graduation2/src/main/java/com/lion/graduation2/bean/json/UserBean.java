package com.lion.graduation2.bean.json;

import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Lion on 2015/4/19.
 */
@Table(name = "user")
public class UserBean {

    //User ID
    private int id;
    //用户帐号
    private String account;
    //用户姓名
    private String name;
    //用户图片服务器中路径
    private String pic;
    //用户图片本地保存路径
    private String pic_path;
    //用户简介
    private String description;
    //联系电话
    private String phone;
    //电子邮箱
    private String email;
    //所属部门
    private String dept;
    //所属职位
    private String position;
    //入职时间
    private String joinTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", pic_path='" + pic_path + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dept='" + dept + '\'' +
                ", position='" + position + '\'' +
                ", joinTime='" + joinTime + '\'' +
                '}';
    }
}
