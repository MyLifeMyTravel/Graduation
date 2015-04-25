package com.lion.graduation2.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 网络连接类
 * Created by Lion on 2015/3/28.
 */
public class HttpUtils {

    /**
     * 网络连接Url参数
     */
    public interface HttpParams {
        String ACCOUNT = "account";
        String PWD = "password";
    }

    /**
     * 网络连接Url常量
     */
    public interface HttpUrl {
        //虚拟机
        //String DOMAIN_URL = "http://192.168.56.1/php/";
        //新浪云
        String DOMAIN_URL = "http://1.wodebiyesheji.sinaapp.com/php/";
        //实体机
        //String DOMAIN_URL = "http://www.littlejie.com/php/";
        String LOGIN_URL = DOMAIN_URL + "login.php?account=%s&password=%s";
        String GET_TASK_URL = DOMAIN_URL + "getTask.php?account=%s";
        String GET_PLACE_URL = DOMAIN_URL + "getPlaceList.php?site_id=%s";
        String POST_HISTORY_URL = DOMAIN_URL + "post/postHistory.php";
        String GET_DEVICE_HISTORY_URL = DOMAIN_URL + "post/getHistory.php";
        String POST_UPDATE_DEVICEID_URL = DOMAIN_URL + "post/updateDeviceID.php";
        String POST_UPDATE_TASK_STATUS_URL = DOMAIN_URL + "post/updateTaskStatus.php";
        String GET_COUNT_URL = DOMAIN_URL + "getTaskCount.php?account=%s";
    }

    public static String httpGet(String url) {
        String result = null;
        //创建一个HttpClient对象，用于发送GET请求
        HttpClient client = new DefaultHttpClient();

        //创建一个HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = client.execute(httpGet);
            //读取状态码
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            //如statusCode = 200,则代表连接成功
            if (statusCode == 200) {
                HttpEntity entity = httpResponse.getEntity();
                InputStream is = entity.getContent();
                result = streamToStr(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 把流对象转换成字符串对象
     *
     * @param is
     * @return
     */
    private static String streamToStr(InputStream is) {
        try {
            // 定义字节数组输出流对象
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            // 定义读取的长度
            int len = 0;
            // 定义读取的缓冲区
            byte buffer[] = new byte[1024];
            // 按照定义的缓冲区进行循环读取，直到读取完毕为止
            while ((len = is.read(buffer)) != -1) {
                // 根据读取的长度写入到字节数组输出流对象中
                os.write(buffer, 0, len);
            }
            // 关闭流
            is.close();
            os.close();
            // 把读取的字节数组输出流对象转换成字节数组
            byte data[] = os.toByteArray();
            // 按照指定的编码进行转换成字符串(此编码要与服务端的编码一致就不会出现乱码问题了，android默认的编码为UTF-8)
            return new String(data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
