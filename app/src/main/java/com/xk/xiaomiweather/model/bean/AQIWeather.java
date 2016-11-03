package com.xk.xiaomiweather.model.bean;

import java.util.Map;

/**
 * 空气质量信息（针对某一个城市）
 * Created by xuekai on 2016/11/2.
 */
public class AQIWeather {
    private Map<String,String> futureDayAQIs;//未来几天的aqi质量指数 key 星期
    private Map<String,String>  lastHourAQIs;//过去24小时aqi质量指数 key 11：20
    private CurrentAQIWeather currentAQIWeather;

    public Map<String, String> getFutureDayAQIs() {
        return futureDayAQIs;
    }

    public void setFutureDayAQIs(Map<String, String> futureDayAQIs) {
        this.futureDayAQIs = futureDayAQIs;
    }

    public Map<String, String> getLastHourAQIs() {
        return lastHourAQIs;
    }

    public void setLastHourAQIs(Map<String, String> lastHourAQIs) {
        this.lastHourAQIs = lastHourAQIs;
    }

    public CurrentAQIWeather getCurrentAQIWeather() {
        return currentAQIWeather;
    }

    public void setCurrentAQIWeather(CurrentAQIWeather currentAQIWeather) {
        this.currentAQIWeather = currentAQIWeather;
    }

    @Override
    public String toString() {
        return "AQIWeather{" +
                "futureDayAQIs=" + futureDayAQIs +
                ", lastHourAQIs=" + lastHourAQIs +
                ", currentAQIWeather=" + currentAQIWeather +
                '}';
    }
}
