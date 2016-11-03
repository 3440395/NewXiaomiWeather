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

    @Override
    public String toString() {
        return "BaseWeather{" +
                "futureDayBaseWeathers=" + futureDayBaseWeathers +
                ", threeHourBaseWeathers=" + threeHourBaseWeathers +
                ", todayBaseWeather=" + todayBaseWeather +
                ", currentBaseWeather=" + currentBaseWeather +
                '}';
    }

    public List<FutureDayBaseWeather> getFutureDayBaseWeathers() {
        return futureDayBaseWeathers;
    }

    public void setFutureDayBaseWeathers(List<FutureDayBaseWeather> futureDayBaseWeathers) {
        this.futureDayBaseWeathers = futureDayBaseWeathers;
    }

    public List<ThreeHourBaseWeather> getThreeHourBaseWeathers() {
        return threeHourBaseWeathers;
    }

    public void setThreeHourBaseWeathers(List<ThreeHourBaseWeather> threeHourBaseWeathers) {
        this.threeHourBaseWeathers = threeHourBaseWeathers;
    }

    public TodayBaseWeather getTodayBaseWeather() {
        return todayBaseWeather;
    }

    public void setTodayBaseWeather(TodayBaseWeather todayBaseWeather) {
        this.todayBaseWeather = todayBaseWeather;
    }

    public CurrentBaseWeather getCurrentBaseWeather() {
        return currentBaseWeather;
    }

    public void setCurrentBaseWeather(CurrentBaseWeather currentBaseWeather) {
        this.currentBaseWeather = currentBaseWeather;
    }
}


