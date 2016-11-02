package com.xk.xiaomiweather.model.bean;

import java.util.List;

/**
 * 基础天气信息（温湿度等，针对某一个城市）
 * Created by xuekai on 2016/11/2.
 */
public class BaseWeather {
    private List<FutureDayBaseWeather> futureDayBaseWeathers;
    private List<ThreeHourBaseWeather> threeHourBaseWeathers;
    private TodayBaseWeather todayBaseWeather;
    private CurrentBaseWeather currentBaseWeather;
}
