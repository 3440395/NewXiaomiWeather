package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.FutureDayBaseWeather;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.ui.IVUpdateable;
import com.xk.xiaomiweather.ui.WeatherForecastActivity;
import com.xk.xiaomiweather.ui.util.IconUtil;

import org.w3c.dom.Text;

import java.util.List;

import static com.xk.xiaomiweather.R.id.aqi_index;

/**
 * Created by xk on 2016/11/7 20:08.
 */

public class WeatherItemView extends RelativeLayout implements IVUpdateable<Weather> {

    private View item;
    private View bottomLine;
    private ImageView icon;
    private TextView time;
    private TextView temp;
    private TextView weather;
    private int index;
    private Weather data;

    public WeatherItemView(Context context,int index) {
        super(context);
        this.index=index;
        init();
    }

    private void init() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WeatherForecastActivity.class);
                intent.putExtra("data",data);
                getContext().startActivity(intent);
            }
        });
        item = View.inflate(getContext(), R.layout.layout_weather_item, null);

        bottomLine = item.findViewById(R.id.bottomLine);
        icon = (ImageView) item.findViewById(R.id.icon);
        time = (TextView) item.findViewById(R.id.time);
        temp = (TextView) item.findViewById(R.id.temp);
        weather = (TextView) item.findViewById(R.id.weather);

        if (index==2) {
            bottomLine.setVisibility(INVISIBLE);

        }else{
            bottomLine.setVisibility(VISIBLE);
        }
        addView(item);
    }




    @Override
    public void update(Weather data) {
        this.data=data;
        FutureDayBaseWeather futureDayBaseWeather = data.getBaseWeather().getFutureDayBaseWeathers().get(index);
        time.setText(index==0?"今天":(index==1?"明天":futureDayBaseWeather.getWeek()));
        weather.setText(futureDayBaseWeather.getWeather());

        String temperature = futureDayBaseWeather.getTemperature();
         temperature = temperature.replace("~", "/");
        temp.setText(temperature);

        IconUtil.setIcon(icon,data.getBaseWeather().getFutureDayBaseWeathers().get(index).getWeather_id()[0]);

    }
}
