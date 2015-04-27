package com.lion.graduation2.util;

import android.os.Environment;

/**
 * 常量类
 * Created by Lion on 2015/3/30.
 */
public class Constant {

    //日志输出TAG
    public static final String TAG = "bysj";

    //数据库名字
    public static final String DB = "bysj";

    //图片后缀
    public static final String PIC_SUFFIX = ".jpg";

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

        //用户头像,服务器路径
        String PIC_URL = "pic_url";

        //用户头像，本地路径
        String PIC_PATH = "pic_path";

        //用户信息
        String DESCRIPTION = "description";

        //联系电话
        String PHONE = "phone";

        //电子邮箱
        String EMAIL = "email";

        //所属部门
        String DEPT = "dept";

        //所属职位
        String POSITION = "position";

        //入职时间
        String JOINTIME = "joinTime";

        //任务信息
        String TASK_INFO = "task_info";

        //场地信息
        String PLACE_INFO = "place_info";
    }

    public interface Path {
        /* 根目录 */
        String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        /* 图片存放路径 */
        String IMAGE = ROOT + "task/pic/";
        /* 未压缩的照片 */
        String RAW_IMAGE = IMAGE + "raw/";
        /* 压缩的照片 */
        String CAPTURE_IMAGE = IMAGE + "capture/";
    }
}
