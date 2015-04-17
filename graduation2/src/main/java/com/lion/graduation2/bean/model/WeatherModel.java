package com.lion.graduation2.bean.model;

/**
 * Created by Lion on 2015/3/19.
 */
public class WeatherModel {

    private String city = null;//城市
    private String weather = null;//天气情况
    private String windDir = null;//风向
    private String windPower = null;//风力
    private String humidity = null;//空气湿度
    private String reportTime = null;//数据发布时间

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "城市:" + city + "\n天气情况:" + weather + "\n风向:" + windDir + "\n风力:" + windPower + "\n空气湿度:" + humidity + "\n发布时间:" + reportTime;
    }
}
