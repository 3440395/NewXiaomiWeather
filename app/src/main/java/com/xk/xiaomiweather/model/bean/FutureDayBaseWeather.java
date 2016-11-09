package com.xk.xiaomiweather.model.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by xuekai on 2016/11/2.
 */

public class FutureDayBaseWeather  implements Serializable {
//        "temperature": "8℃~20℃",
//        "weather": "晴转霾",
//        "weather_id": {
//            "fa": "00",
//            "fb": "53"
//        },
//        "wind": "西南风微风",
//        "week": "星期五",
//        "date": "20140321"
    private String temperature;
    private String weather;
    private String[] weather_id;//json中是 weather_id{fa:00,fb:01}
    private String wind;
    private String week;
    private String date;

    @Override
    public String toString() {
        return "FutureDayBaseWeather{" +
                "temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", weather_id=" + Arrays.toString(weather_id) +
                ", wind='" + wind + '\'' +
                ", week='" + week + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String[] getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String[] weather_id) {
        this.weather_id = weather_id;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
