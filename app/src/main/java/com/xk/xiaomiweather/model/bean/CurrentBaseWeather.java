package com.xk.xiaomiweather.model.bean;

/**
 * 实况天气
 * Created by xuekai on 2016/11/2.
 */
public class CurrentBaseWeather {
    /**
     * 当前温度
     */
    private String temp;
    /**
     * 风向 西风
     */
    private String wind_direction;
    /**
     * 风力 2级
     */
    private String wind_strength;
    /**
     * 当前湿度
     */
    private String humidity;
    /**
     * 更新时间
     */
    private String time;

    @Override
    public String toString() {
        return "CurrentBaseWeather{" +
                "temp='" + temp + '\'' +
                ", wind_direction='" + wind_direction + '\'' +
                ", wind_strength='" + wind_strength + '\'' +
                ", humidity='" + humidity + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_strength() {
        return wind_strength;
    }

    public void setWind_strength(String wind_strength) {
        this.wind_strength = wind_strength;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
