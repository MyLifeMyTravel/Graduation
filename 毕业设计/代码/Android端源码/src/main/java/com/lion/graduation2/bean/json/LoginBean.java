package com.lion.graduation2.bean.json;

/**
 * 登录数据传输对象
 * Created by Lion on 2015/3/29.
 */
public class LoginBean {

    private String flag;
    private UserBean user;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Login{" +
                "flag='" + flag + '\'' +
                ", user=" + user +
                '}';
    }
}
