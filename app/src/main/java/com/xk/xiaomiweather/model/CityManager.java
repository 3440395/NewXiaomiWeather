package com.xk.xiaomiweather.model;

import android.content.Context;

import com.xk.xiaomiweather.provider.WeatherProvider;

/**
 * Created by xk on 2016/11/2 20:25.
 */

public class CityManager {
    private static CityManager instance;
    private static WeatherProvider weatherProvider;
    private CityManager(){}
    public static CityManager getInstance(Context appContext){
        if (instance==null) {
            instance=new CityManager();
        }
        if (weatherProvider == null) {
            weatherProvider=new WeatherProvider();
        }
        return instance;
    }

    /**
     * 通过城市名添加一个城市
     * @param district
     */
    public void addCity(String district){

    }
    /**
     * 通过城市名删除一个城市
     * @param district
     */
    public void deleteCity(String district){

    }

    /**
     * app第一次启动的时候需要初始化城市列表
     */
    public void initCityList(){

    }
}
