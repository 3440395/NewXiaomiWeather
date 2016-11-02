package com.xk.xiaomiweather.model.bean;

/**
 * 今天的天气
 * Created by xuekai on 2016/11/2.
 */
public class TodayBaseWeather {


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
}
