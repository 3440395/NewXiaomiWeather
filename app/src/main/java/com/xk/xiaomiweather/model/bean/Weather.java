package com.xk.xiaomiweather.model.bean;

/**
 * 天气信息（针对某一个城市）
 * Created by xuekai on 2016/11/1.
 */

public class Weather {
    private City city;
    private BaseWeather baseWeather;
    private AQIWeather aqiWeather;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public BaseWeather getBaseWeather() {
        return baseWeather;
    }

    public void setBaseWeather(BaseWeather baseWeather) {
        this.baseWeather = baseWeather;
    }

    public AQIWeather getAqiWeather() {
        return aqiWeather;
    }

    public void setAqiWeather(AQIWeather aqiWeather) {
        this.aqiWeather = aqiWeather;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city=" + city +
                ", baseWeather=" + baseWeather +
                ", aqiWeather=" + aqiWeather +
                '}';
    }
}
