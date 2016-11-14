package com.xk.xiaomiweather.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.ui.custom.FutureWeatherItem;
import com.xk.xiaomiweather.ui.custom.WeatherForecastView;
import com.xk.xiaomiweather.ui.util.StatusColorUtil;

/**
 * Created by xuekai on 2016/11/9.
 */

public class WeatherForecastActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Weather data = (Weather) getIntent().getSerializableExtra("data");
        StatusColorUtil.setStatusBarTextStyle(this,true);
        WeatherForecastView weatherForecastView = new WeatherForecastView(this);
        weatherForecastView.update(data.getBaseWeather().getFutureDayBaseWeathers());

        addContentView(weatherForecastView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
