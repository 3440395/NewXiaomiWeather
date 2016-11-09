package com.xk.xiaomiweather.model.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 今天的天气
 * Created by xuekai on 2016/11/2.
 */
public class TodayBaseWeather  implements Serializable {


    /**
     * xxxx年xx月xx日
     */
    private String date_y;
    /**
     * 星期x
     */
    private String week;
    /**
     * 8℃~20℃  今日温度
     */
    private String temperature;
    /**
     * "晴转霾",	今日天气
     */
    private String weather;
    /**
     * 天气唯一标识
     * "fa": "00",	天气标识00：晴 "fb": "53"天气标识53：霾 如果fa不等于fb，说明是组合天气
     */
    private String[] weather_id;//json中是  "weather_id": { "fa": "00","fb": "53"}
    /**
     * "西南风微风",
     */
    private String wind;
    /**
     * "较冷", 穿衣指数
     */


    private String dressing_index;
    /**
     * "建议着大衣、呢外套加毛衣、卫衣等服装。",	穿衣建议
     */


    private String dressing_advice;
    /**
     * "中等",	紫外线强度
     */
    private String uv_index;
    /**
     *
     */
    private String comfort_index;
    /**
     * "较适宜",	洗车指数
     */
    private String wash_index;
    /**
     * 适宜",	旅游指数
     */
    private String travel_index;
    /**
     * "": "较适宜",晨练指数
     */
    private String exercise_index;
    /**
     *
     */
    private String drying_index;

    public String getDate_y() {
        return date_y;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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

    public String getDressing_index() {
        return dressing_index;
    }

    public void setDressing_index(String dressing_index) {
        this.dressing_index = dressing_index;
    }

    public String getDressing_advice() {
        return dressing_advice;
    }

    public void setDressing_advice(String dressing_advice) {
        this.dressing_advice = dressing_advice;
    }

    public String getUv_index() {
        return uv_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getComfort_index() {
        return comfort_index;
    }

    public void setComfort_index(String comfort_index) {
        this.comfort_index = comfort_index;
    }

    public String getWash_index() {
        return wash_index;
    }

    public void setWash_index(String wash_index) {
        this.wash_index = wash_index;
    }

    public String getTravel_index() {
        return travel_index;
    }

    public void setTravel_index(String travel_index) {
        this.travel_index = travel_index;
    }

    public String getExercise_index() {
        return exercise_index;
    }

    public void setExercise_index(String exercise_index) {
        this.exercise_index = exercise_index;
    }

    public String getDrying_index() {
        return drying_index;
    }

    public void setDrying_index(String drying_index) {
        this.drying_index = drying_index;
    }

    @Override
    public String toString() {
        return "TodayBaseWeather{" +
                "date_y='" + date_y + '\'' +
                ", week='" + week + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", weather_id=" + Arrays.toString(weather_id) +
                ", wind='" + wind + '\'' +
                ", dressing_index='" + dressing_index + '\'' +
                ", dressing_advice='" + dressing_advice + '\'' +
                ", uv_index='" + uv_index + '\'' +
                ", comfort_index='" + comfort_index + '\'' +
                ", wash_index='" + wash_index + '\'' +
                ", travel_index='" + travel_index + '\'' +
                ", exercise_index='" + exercise_index + '\'' +
                ", drying_index='" + drying_index + '\'' +
                '}';
    }
}
