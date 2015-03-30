package com.lion.graduation.util;

/**
 * 常量类
 * Created by Lion on 2015/3/30.
 */
public class Constant {

    //日志输出TAG
    public static final String TAG = "lion";

    /**
     * 程序用到的Key
     */
    public interface Key {

        //用户ID，MySQl数据库自增
        String ID = "id";

        //用户帐号
        String ACCOUNT = "account";

        //用户姓名
        String NAME = "name";

        //用户头像
        String PIC = "pic";

        //用户信息
        String INFO = "info";
    }
}
