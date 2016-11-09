package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.xk.xiaomiweather.ui.IVUpdateable;

/**
 * Created by xuekai on 2016/11/9.
 */

public class PolluteDetialView extends FrameLayout implements IVUpdateable<CurrentAQIWeather> {
    public PolluteDetialView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.layout_pollute_detail, this);
    }

    @Override
    public void update(CurrentAQIWeather data) {
        String aqi = data.getAQI();
        String aqiDes = "";
        if (aqi != null) {
            int iaqi = Integer.parseInt(aqi);
            if (iaqi >= 0 && iaqi <= 50) {
                aqiDes = "优";
            } else if (iaqi > 50 && iaqi <= 100) {
                aqiDes = "良";

            } else if (iaqi > 100 && iaqi <= 150) {
                aqiDes = "中度污染";
            } else {
                aqiDes = "重度污染";
            }
        }
        ((TextView) findViewById(R.id.pollute_level)).setText(aqiDes);
        ((TextView) findViewById(R.id.pm25)).setText(data.getPM25());
        ((TextView) findViewById(R.id.pm10)).setText(data.getPM10());
        ((TextView) findViewById(R.id.no2)).setText(data.getNO2());
        ((TextView) findViewById(R.id.so2)).setText(data.getSO2());
        ((TextView) findViewById(R.id.co)).setText(data.getCO());
        ((TextView) findViewById(R.id.o3)).setText(data.getO3());
    }
}
