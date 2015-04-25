package com.lion.graduation2.bean.json;

/**
 * 设备巡检历史记录
 * Created by Lion on 2015/4/25.
 */
public class DeviceHistoryBean {

    private int xsnr_id;
    private String xsnr_nr;
    private int count;

    public int getXsnr_id() {
        return xsnr_id;
    }

    public void setXsnr_id(int xsnr_id) {
        this.xsnr_id = xsnr_id;
    }

    public String getXsnr_nr() {
        return xsnr_nr;
    }

    public void setXsnr_nr(String xsnr_nr) {
        this.xsnr_nr = xsnr_nr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DeviceHistoryBean{" +
                "xsnr_id=" + xsnr_id +
                ", xsnr_nr='" + xsnr_nr + '\'' +
                ", count=" + count +
                '}';
    }
}
