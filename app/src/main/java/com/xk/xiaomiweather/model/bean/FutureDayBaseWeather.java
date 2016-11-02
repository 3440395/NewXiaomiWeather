package com.xk.xiaomiweather.model.bean;

/**
 * Created by xuekai on 2016/11/2.
 */

public class FutureDayBaseWeather {
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
}
