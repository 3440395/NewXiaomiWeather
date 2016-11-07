package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.widget.RelativeLayout;

import com.xk.xiaomiweather.model.bean.FutureDayBaseWeather;
import com.xk.xiaomiweather.ui.IVUpdateable;

/**
 * Created by xk on 2016/11/7 20:08.
 */

public class WeatherItemView extends RelativeLayout implements IVUpdateable<FutureDayBaseWeather> {
    public WeatherItemView(Context context) {
        super(context);
    }

    @Override
    public void update(FutureDayBaseWeather data) {

    }
}
