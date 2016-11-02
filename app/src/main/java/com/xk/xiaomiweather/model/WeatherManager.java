package com.xk.xiaomiweather.model;

import android.content.Context;

import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.provider.WeatherProvider;

/**
 * Created by xuekai on 2016/11/1.
 */
public class WeatherManager {
    private static WeatherManager instance;
    private static WeatherProvider weatherProvider;
    private WeatherManager(){}
    public static WeatherManager getInstance(Context appContext){
        if (instance==null) {
            instance=new WeatherManager();
        }
        if (weatherProvider == null) {
            weatherProvider=new WeatherProvider();
        }
        return instance;
    }
    /**
     * 从内容提供者中获取数据
     * @author xk
     * @time 2016/11/1 13:31
     */
    public Weather getWeather(String cityName){
        return null;
    }
    /**
     * 从网络上请求天气数据（由多个请求组成，调用多个三方api，每一个请求成功后都会做一个标记，当请求完全完成之后，
     * 代表数据请求完毕，写入内容提供者，并且让内容提供者notify）
     * @author xk
     * @time 2016/11/1 13:32
     */
    public void refreshWeather(){

    }


//    内容提供者notify会提醒最外层的view，让他去更新ui，然后他调用内层的update
//    view在初始化的时候会调用getWeather 获取本地数据，然后显示。
//    刷新的时候回调用 requestWeatherFromeNet
}
