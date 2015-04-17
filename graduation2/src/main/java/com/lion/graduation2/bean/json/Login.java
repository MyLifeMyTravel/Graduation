package com.lion.graduation2.bean.json;

/**
 * 登录数据传输对象
 * Created by Lion on 2015/3/29.
 */
public class Login {

    private String flag;
    private UserDto user;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public static class UserDto {
        private String id;
        private String account;
        private String name;
        private String pic;
        private String info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "UserDto{" +
                    "id='" + id + '\'' +
                    ", account='" + account + '\'' +
                    ", name='" + name + '\'' +
                    ", pic='" + pic + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "flag='" + flag + '\'' +
                ", user=" + user +
                '}';
    }
}
